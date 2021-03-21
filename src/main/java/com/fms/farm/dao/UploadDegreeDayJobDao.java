package com.fms.farm.dao;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fms.farm.util.UploadJobStatus;

@DynamoDBTable(tableName = "upload_degree_day")
public class UploadDegreeDayJobDao {
	String id;
	String createDate;
	String updateDate;
	String jobStatus;
	String request;
	String path;
	Character delimitter;
	String region;

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@DynamoDBAttribute
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@DynamoDBAttribute
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
//    @DynamoDBIndexHashKey(globalSecondaryIndexName = "GSI")
    @DynamoDBAttribute
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String status) {
		this.jobStatus = status;
	}
	@DynamoDBAttribute
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	@DynamoDBAttribute
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@DynamoDBAttribute
	public Character getDelimitter() {
		return delimitter;
	}
	public void setDelimitter(Character delimitter) {
		this.delimitter = delimitter;
	}
	@DynamoDBAttribute
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public UploadDegreeDayJobDao(String id, String createDate, String updateDate, String status, String request,
			String path, Character delimitter, String region) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.jobStatus = status;
		this.request = request;
		this.path = path;
		this.delimitter = delimitter;
		this.region = region;
	}
	public UploadDegreeDayJobDao() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	
	
	
	
}
