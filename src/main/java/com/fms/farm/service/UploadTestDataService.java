package com.fms.farm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fms.farm.dao.UploadJobStatusDynamoDb;
import com.fms.farm.entities.request.TestDataRequest;

@Service
public class UploadTestDataService {
	@Autowired
	public DynamoDBMapper dynamoDBMapper;
	
	
	
	public void addDataToDynamoDB(TestDataRequest request) {
		UploadJobStatusDynamoDb status = new UploadJobStatusDynamoDb(request.getId(), request.getStatus(), request.getCreateDate(), request.getEndDate());
		dynamoDBMapper.save(status);
	}
	
}
