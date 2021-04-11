package com.fms.farm.entities;

public class Egg {

	String id;
	Double date;//YYYYMMDD
	Double meanAirTemperature;
	Double vapourPressureDeficit;
	Double netRadiation;
	Double windSpeed;
	String regionOfAnalysis;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getDate() {
		return date;
	}
	public void setDate(Double date) {
		this.date = date;
	}
	public Double getMeanAirTemperature() {
		return meanAirTemperature;
	}
	public void setMeanAirTemperature(Double meanAirTemperature) {
		this.meanAirTemperature = meanAirTemperature;
	}
	public Double getVapourPressureDeficit() {
		return vapourPressureDeficit;
	}
	public void setVapourPressureDeficit(Double vapourPressureDeficit) {
		this.vapourPressureDeficit = vapourPressureDeficit;
	}
	public Double getNetRadiation() {
		return netRadiation;
	}
	public void setNetRadiation(Double netRadiation) {
		this.netRadiation = netRadiation;
	}
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
	public Egg(String id, Double date, Double meanAirTemperature, Double vapourPressureDeficit, Double netRadiation,
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
