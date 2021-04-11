package com.fms.farm.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "egg_model")
public class EggDao {

	String id;
	Double date;//YYYYMMDD
	Double meanAirTemperature;
	Double vapourPressureDeficit;
	Double netRadiation;
	Double windSpeed;
	String regionOfAnalysis;
	@DynamoDBHashKey(attributeName = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@DynamoDBAttribute
	public Double getDate() {
		return date;
	}
	public void setDate(Double date) {
		this.date = date;
	}
	@DynamoDBAttribute
	public Double getMeanAirTemperature() {
		return meanAirTemperature;
	}
	public void setMeanAirTemperature(Double meanAirTemperature) {
		this.meanAirTemperature = meanAirTemperature;
	}
	@DynamoDBAttribute
	public Double getVapourPressureDeficit() {
		return vapourPressureDeficit;
	}
	public void setVapourPressureDeficit(Double vapourPressureDeficit) {
		this.vapourPressureDeficit = vapourPressureDeficit;
	}
	@DynamoDBAttribute
	public Double getNetRadiation() {
		return netRadiation;
	}
	public void setNetRadiation(Double netRadiation) {
		this.netRadiation = netRadiation;
	}
	@DynamoDBAttribute
	public Double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getRegionOfAnalysis() {
		return regionOfAnalysis;
	}
	public void setRegionOfAnalysis(String regionOfAnalysis) {
		this.regionOfAnalysis = regionOfAnalysis;
	}
	public EggDao(String id, Double date, Double meanAirTemperature, Double vapourPressureDeficit, Double netRadiation,
			Double windSpeed, String regionOfAnalysis) {
		super();
		this.id = id;
		this.date = date;
		this.meanAirTemperature = meanAirTemperature;
		this.vapourPressureDeficit = vapourPressureDeficit;
		this.netRadiation = netRadiation;
		this.windSpeed = windSpeed;
		this.regionOfAnalysis = regionOfAnalysis;
	}


	
	
}
