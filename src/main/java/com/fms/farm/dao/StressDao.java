package com.fms.farm.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "stress_model")
public class StressDao {

	String id;
	Integer date;
	Double eto;
	Double eta;
	Double etc;
	String regionOfAnalysis;
	
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
	public Double getEto() {
		return eto;
	}
	public void setEto(Double eto) {
		this.eto = eto;
	}
	@DynamoDBAttribute
	public Double getEta() {
		return eta;
	}
	public void setEta(Double eta) {
		this.eta = eta;
	}
	@DynamoDBAttribute
	public Double getEtc() {
		return etc;
	}
	public void setEtc(Double etc) {
		this.etc = etc;
	}
	@DynamoDBAttribute
	public String getRegionOfAnalysis() {
		return regionOfAnalysis;
	}
	public void setRegionOfAnalysis(String regionOfAnalysis) {
		this.regionOfAnalysis = regionOfAnalysis;
	}
	public StressDao(String id, Integer date, Double eto, Double eta, Double etc, String regionOfAnalysis) {
		super();
		this.id = id;
		this.date = date;
		this.eto = eto;
		this.eta = eta;
		this.etc = etc;
		this.regionOfAnalysis = regionOfAnalysis;
	}

	
}
