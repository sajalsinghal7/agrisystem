package com.fms.farm.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "upload_job_status")
public class UploadJobStatusDynamoDb {
	String id;
	String status;
	String createDate;
	String endDate;
	
	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@DynamoDBAttribute
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@DynamoDBAttribute
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@DynamoDBAttribute
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public UploadJobStatusDynamoDb(String id, String status, String createDate, String endDate) {
		super();
		this.id = id;
		this.status = status;
		this.createDate = createDate;
		this.endDate = endDate;
	}
	
	
}
