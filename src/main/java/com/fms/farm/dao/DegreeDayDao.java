package com.fms.farm.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "degree_day")
public class DegreeDayDao {

	String id; // region.date
	String date;//2016-12-31 --> 20161231
	String regionOfAnalysis;
	String tMin;
	String tMax;
	String tMedium;
	String precipitation;
	String degreeDay;
	
	

	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@DynamoDBAttribute
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
//	@DynamoDBIndexHashKey(attributeName = "regionOfAnalysis", globalSecondaryIndexName = "regionOfAnalysis")
	@DynamoDBAttribute
	public String getRegionOfAnalysis() {
		return regionOfAnalysis;
	}
	public void setRegionOfAnalysis(String regionOfAnalysis) {
		this.regionOfAnalysis = regionOfAnalysis;
	}
	@DynamoDBAttribute
	public String gettMin() {
		return tMin;
	}
	public void settMin(String tMin) {
		this.tMin = tMin;
	}
	@DynamoDBAttribute
	public String gettMax() {
		return tMax;
	}
	public void settMax(String tMax) {
		this.tMax = tMax;
	}
	@DynamoDBAttribute
	public String gettMedium() {
		return tMedium;
	}
	public void settMedium(String tMedium) {
		this.tMedium = tMedium;
	}
	@DynamoDBAttribute
	public String getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}
	@DynamoDBAttribute
	public String getDegreeDay() {
		return degreeDay;
	}
	public void setDegreeDay(String degreeDay) {
		this.degreeDay = degreeDay;
	}
	public DegreeDayDao(String id, String date, String regionOfAnalysis, String tMin, String tMax, String tMedium,
			String precipitation, String degreeDay) {
		super();
		this.id = id;
		this.date = date;
		this.regionOfAnalysis = regionOfAnalysis;
		this.tMin = tMin;
		this.tMax = tMax;
		this.tMedium = tMedium;
		this.precipitation = precipitation;
		this.degreeDay = degreeDay;
	}
	
	
}
