package com.fms.farm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fms.farm.dao.EggDao;
import com.fms.farm.entities.Egg;
import com.fms.farm.entities.request.FileUploadRequest;

@Service
public class EggModelHandler implements ModelHandler {


	@Autowired
	public AmazonDynamoDB amazonDynamoDB;
	
	@Autowired
	public DynamoDBMapper dbMapper;
	
	@Override
	public String uploadModel(FileUploadRequest request) {
		streamReadingFsSyncWritingDB(request.getPath(), request.getDelimitter(), request.getRegion().trim().toUpperCase());
		return "Upload Successfull";
	}
	

	@Override
	public void streamReadingFsSyncWritingDB(String pathRequest, String delimitter, String region) {
		String del = delimitter;
		Path path = Paths.get(pathRequest);
		try {
//			Note - Disabling parallel processing as Dynamodb cannot handel the load
//			Stream<String> line  = Files.lines(path).parallel();

			Stream<String> line  = Files.lines(path);
			line.forEach(l -> parseObjectAndUploadToDB(l, del, region));
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Upload Completed for filePath " + pathRequest);
	}
	
	
	@Override
	public void parseObjectAndUploadToDB(String line, String del, String region) {
		String[] arr = line.split(del);
//		System.out.print("--" + arr[0].trim());
//		System.out.print("##" + Integer.valueOf(arr[0].trim()));
		try {
		EggDao dao = new EggDao(region + arr[0].trim(), Double.valueOf(arr[0].trim()), Double.valueOf(arr[1].trim()), Double.valueOf(arr[2].trim()), Double.valueOf(arr[3].trim()), Double.valueOf(arr[4].trim()),region);
		dbMapper.save(dao);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	

	public List<Egg> getAllDegreeDayForRegion(String regionOfAnalysis) {
		Map<String, AttributeValue> expressionAttributeValues =
			    new HashMap<String, AttributeValue>();
			expressionAttributeValues.put(":v_regionOfAnalysis", new AttributeValue().withS(regionOfAnalysis));
		ScanRequest scanRequest = new ScanRequest()
				.withTableName("egg_model")
				.withFilterExpression("regionOfAnalysis = :v_regionOfAnalysis")
				.withExpressionAttributeValues(expressionAttributeValues);
		ScanResult result = amazonDynamoDB.scan(scanRequest);
		List<Egg> resultList = new ArrayList<Egg>();
		for (Map<String, AttributeValue> item : result.getItems()) {
			try {
				   resultList.add(new Egg(item.get("id").getS(), Double.valueOf(item.get("date").getN()), Double.valueOf(item.get("meanAirTemperature").getN()), Double.valueOf(item.get("vapourPressureDeficit").getN()), Double.valueOf(item.get("netRadiation").getN()), Double.valueOf(item.get("windSpeed").getN()), item.get("regionOfAnalysis").getS()));
			} catch(Exception e) {
				e.printStackTrace();
			}
		 }
		return resultList;
	}
}
