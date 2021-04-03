package com.fms.farm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.fms.farm.dao.DegreeDayDao;
import com.fms.farm.dao.UploadDegreeDayJobDao;
import com.fms.farm.dao.UploadJobStatusDynamoDb;
import com.fms.farm.entities.DegreeDay;
import com.fms.farm.entities.request.TestDataRequest;
import com.fms.farm.entities.request.UploadDegreeDay;
import com.fms.farm.entities.request.UploadTestFileRequest;
import com.fms.farm.entities.response.DegreeDayUploadStatus;
import com.fms.farm.util.FormattedDateGenerator;
import com.fms.farm.util.IdGenerator;
import com.fms.farm.util.UploadJobStatus;

@Service
public class UploadDegreeDayHandler {

	@Autowired
	public DynamoDBMapper dbMapper;
	
	@Autowired
	public AmazonDynamoDB amazonDynamoDB;
	
	public DegreeDayUploadStatus createUploadRequest(UploadDegreeDay degreeDay) {
		UploadDegreeDayJobDao dao =new UploadDegreeDayJobDao(IdGenerator.generateId(), FormattedDateGenerator.getDateTimeNow(), FormattedDateGenerator.getDateTimeNow(), UploadJobStatus.SUBMITTED.name(), degreeDay.toString(), degreeDay.getPath(), degreeDay.getDelimitter(), degreeDay.getRegion());
		dbMapper.save(dao);
		return new DegreeDayUploadStatus(dao.getId(), dao.getCreateDate(), dao.getUpdateDate(), dao.getJobStatus(), degreeDay.getPath(), degreeDay.getDelimitter(), degreeDay.getRegion());
	}
	
	
	public void updateFirstSubmitted() {
		// fetch list and find first
		UploadDegreeDayJobDao dao = findFirstFromList();
		// update from submitted to inprogress
		if(dao != null) {

			System.out.println("updating status for " + dao.getId());
			updateJobStatus(dao, "STARTED");
		}
		// update from inprogress to success
		if(dao != null) {
			streamReadingFsSyncWritingDB(dao.getPath(), dao.getDelimitter().toString(), dao.getRegionOfAnalysis());
		}
		
	}
	
	public UploadDegreeDayJobDao findFirstFromList() {
		System.out.println("Finding New Job to upload degree day");
		ScanRequest scanRequest = new ScanRequest()
		    .withTableName("upload_degree_day");
		List<UploadDegreeDayJobDao> listOfSubmittedJobs = new ArrayList<UploadDegreeDayJobDao>();
		ScanResult result = amazonDynamoDB.scan(scanRequest);
		for (Map<String, AttributeValue> tmp : result.getItems()){
		    if(tmp.get("jobStatus").getS().toString().equals("SUBMITTED")) {
		    	System.out.println("Request to process => " + tmp.toString());
		    	listOfSubmittedJobs.add(new UploadDegreeDayJobDao(tmp.get("id").getS().toString(),tmp.get("createDate").getS().toString(),tmp.get("updateDate").getS().toString(),tmp.get("jobStatus").getS().toString(),tmp.get("request").getS().toString(),tmp.get("path").getS().toString(),tmp.get("delimitter").getS().toString().charAt(0),tmp.get("regionOfAnalysis").getS().toString()));
		    }
		}
		if(listOfSubmittedJobs.size() > 0) return listOfSubmittedJobs.get(0);
		return null;
	}
	
	public void updateJobStatus(UploadDegreeDayJobDao dao, String status) {
	    Map<String, AttributeValue> key = new HashMap<>();
	    key.put("id", new AttributeValue().withS(dao.getId()));

	    Map<String, AttributeValue> attributeValues = new HashMap<>();
	    attributeValues.put(":jobStatus", new AttributeValue().withS(status));

	    UpdateItemRequest updateItemRequest = new UpdateItemRequest()
	        .withTableName("upload_degree_day")
	        .withKey(key)
	        .withUpdateExpression("set jobStatus = :jobStatus")
	        .withExpressionAttributeValues(attributeValues);
	    UpdateItemResult updateItemResult = amazonDynamoDB.updateItem(updateItemRequest);
	}
	
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
	
	public void parseObjectAndUploadToDB(String line, String del, String region) {
		String[] arr = line.split(del);
		DegreeDayDao dao = new DegreeDayDao(region + arr[0], arr[0].toString(), region, arr[2].toString(), arr[1].toString(), arr[3].toString(), arr[4].toString(), calculateDegreeDay(Double.valueOf(arr[1]), Double.valueOf(arr[2])));
		dbMapper.save(dao);
	}
	
	public String calculateDegreeDay(Double tmax, Double tmin) {
		Double tbase = (double)10;
		Double result = (double)0;
		if(tmax < tbase) return (new Double(0)).toString();
		else {
			if(tmin<tbase) {
				result = ((tmax + tbase)/2) - tbase;
			} else {
				result = ((tmax + tmin)/2) -tbase;
			}
		}
		return result.toString();
	}
	
	public List<DegreeDay> getAllDegreeDayForRegion(String regionOfAnalysis) {
		Map<String, AttributeValue> expressionAttributeValues =
			    new HashMap<String, AttributeValue>();
			expressionAttributeValues.put(":v_regionOfAnalysis", new AttributeValue().withS(regionOfAnalysis));
		ScanRequest scanRequest = new ScanRequest()
				.withTableName("degree_day")
				.withFilterExpression("regionOfAnalysis = :v_regionOfAnalysis")
				.withExpressionAttributeValues(expressionAttributeValues);
		ScanResult result = amazonDynamoDB.scan(scanRequest);
		List<DegreeDay> resultList = new ArrayList<DegreeDay>();
		for (Map<String, AttributeValue> item : result.getItems()) {
		    System.out.println(item.toString());
		    resultList.add(new DegreeDay(item.get("id").getS(), Integer.valueOf(item.get("date").getS()), item.get("regionOfAnalysis").getS(), Double.valueOf(item.get("tMin").getS()), Double.valueOf(item.get("tMax").getS()), Double.valueOf(item.get("tMedium").getS()), Double.valueOf(item.get("precipitation").getS()), Double.valueOf(item.get("degreeDay").getS())));
		}
		return resultList;
	}

	
}
