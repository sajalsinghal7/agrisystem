package com.fms.farm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
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
import com.fms.farm.dao.UploadDegreeDayJobDao;
import com.fms.farm.dao.UploadJobStatusDynamoDb;
import com.fms.farm.entities.DegreeDay;
import com.fms.farm.entities.request.UploadDegreeDay;
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
	}
	
	public UploadDegreeDayJobDao findFirstFromList() {
		System.out.println("Finding New Job to upload degree day");
//		DynamoDB db = new DynamoDB(amazonDynamoDB);
//		Table table = db.getTable("upload_degree_day");
//		QuerySpec querySpec = new QuerySpec();
//		querySpec.withKeyConditionExpression("jobStatus = :v_jobStatus");
//		querySpec.withValueMap(new ValueMap().withString(":v_jobStatus", "SUBMITTED"));
//		ItemCollection<QueryOutcome> uploadedJobStatus = table.query(querySpec);
//		Iterator<Item> iterator = uploadedJobStatus.iterator();
//		UploadDegreeDayJobDao result = null;
//		if (iterator.hasNext()) {
//            Map<String, Object> tmp = iterator.next().asMap();
//            result = (new UploadDegreeDayJobDao(tmp.get("id").toString(),tmp.get("createDate").toString(),tmp.get("updateDate").toString(),tmp.get("jobStatus").toString(),tmp.get("request").toString(),tmp.get("path").toString(),tmp.get("delimitter").toString().charAt(0),tmp.get("region").toString() ));
//        }
//		
//		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

		ScanRequest scanRequest = new ScanRequest()
		    .withTableName("upload_degree_day");
		List<UploadDegreeDayJobDao> listOfSubmittedJobs = new ArrayList<UploadDegreeDayJobDao>();
		ScanResult result = amazonDynamoDB.scan(scanRequest);
		for (Map<String, AttributeValue> tmp : result.getItems()){
			System.out.println(tmp.toString());
			System.out.print(tmp.get("jobStatus").getS());
		    if(tmp.get("jobStatus").getS().toString().equals("SUBMITTED")) {
		    	listOfSubmittedJobs.add(new UploadDegreeDayJobDao(tmp.get("id").getS().toString(),tmp.get("createDate").getS().toString(),tmp.get("updateDate").getS().toString(),tmp.get("jobStatus").getS().toString(),tmp.get("request").getS().toString(),tmp.get("path").getS().toString(),tmp.get("delimitter").getS().toString().charAt(0),tmp.get("region").getS().toString()));
		    }
		}
		System.out.print(listOfSubmittedJobs.size());
		if(listOfSubmittedJobs.size() > 0) return listOfSubmittedJobs.get(0);
		return null;
	}
	
	public void updateJobStatus(UploadDegreeDayJobDao dao, String status) {
//		Map<String,AttributeValue> attributeValues = new HashMap<>();
//	    attributeValues.put("id",new AttributeValue().withS(dao.getId()));
//	    attributeValues.put("jobStatus",new AttributeValue().withS(status));
//	    UpdateItemRequest updateItemRequest = new UpdateItemRequest()
//	            .withTableName("upload_degree_day")
//	            .addKeyEntry("id",new AttributeValue().withS(dao.getId()))
//	            .addAttributeUpdatesEntry("jobStatus",
//	                new AttributeValueUpdate().withValue(new AttributeValue().withS(status)));
//
//	    UpdateItemResult updateItemResult = amazonDynamoDB.updateItem(updateItemRequest);
		
	    Map<String, AttributeValue> key = new HashMap<>();
	    key.put("id", new AttributeValue().withS(dao.getId()));

	    Map<String, AttributeValue> attributeValues = new HashMap<>();
	    attributeValues.put(":jobStatus", new AttributeValue().withS(status));
//	    attributeValues.put(":fullname", new AttributeValue().withS(fullName));

	    UpdateItemRequest updateItemRequest = new UpdateItemRequest()
	        .withTableName("upload_degree_day")
	        .withKey(key)
	        .withUpdateExpression("set jobStatus = :jobStatus")
	        .withExpressionAttributeValues(attributeValues);
	    UpdateItemResult updateItemResult = amazonDynamoDB.updateItem(updateItemRequest);
	}
}
