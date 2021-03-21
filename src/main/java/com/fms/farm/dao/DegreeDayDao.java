package com.fms.farm.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "degree_day")
public class DegreeDayDao {

	String id; // region.date
	Integer date;//2016-12-31 --> 20161231
	String region;
	Double tMin;
	Double tMax;
	Double tMedium;
	Double precipitation;
	

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@DynamoDBAttribute
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	@DynamoDBAttribute
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@DynamoDBAttribute
	public Double gettMin() {
		return tMin;
	}
	public void settMin(Double tMin) {
		this.tMin = tMin;
	}
	@DynamoDBAttribute
	public Double gettMax() {
		return tMax;
	}
	public void settMax(Double tMax) {
		this.tMax = tMax;
	}
	@DynamoDBAttribute
	public Double gettMedium() {
		return tMedium;
	}
	public void settMedium(Double tMedium) {
		this.tMedium = tMedium;
	}
	@DynamoDBAttribute
	public Double getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}
	public DegreeDayDao(String id, Integer date, String region, Double tMin, Double tMax, Double tMedium,
			Double precipitation) {
		super();
		this.id = id;
		this.date = date;
		this.region = region;
		this.tMin = tMin;
		this.tMax = tMax;
		this.tMedium = tMedium;
		this.precipitation = precipitation;
	}
	
	
}
