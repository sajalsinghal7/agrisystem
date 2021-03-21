package com.fms.farm.entities;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

public class DegreeDay extends RepresentationModel<EntityModel<DegreeDay>> {
	String id; // region.date
	Integer date;//2016-12-31 --> 20161231
	String region;
	Double tMin;
	Double tMax;
	Double tMedium;
	Double precipitation;
	Double degreeDay;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Double gettMin() {
		return tMin;
	}
	public void settMin(Double tMin) {
		this.tMin = tMin;
	}
	public Double gettMax() {
		return tMax;
	}
	public void settMax(Double tMax) {
		this.tMax = tMax;
	}
	public Double gettMedium() {
		return tMedium;
	}
	public void settMedium(Double tMedium) {
		this.tMedium = tMedium;
	}
	public Double getPrecipitation() {
		return precipitation;
	}
	public void setPrecipitation(Double precipitation) {
		this.precipitation = precipitation;
	}

	


	public Double getDegreeDay() {
		return degreeDay;
	}
	public void setDegreeDay(Double degreeDay) {
		this.degreeDay = degreeDay;
	}
	
	
	public DegreeDay(String id, Integer date, String region, Double tMin, Double tMax, Double tMedium,
			Double precipitation, Double degreeDay) {
		super();
		this.id = id;
		this.date = date;
		this.region = region;
		this.tMin = tMin;
		this.tMax = tMax;
		this.tMedium = tMedium;
		this.precipitation = precipitation;
		this.degreeDay = degreeDay;
	}
	public String generateId(String region, Integer date) {
		return region + "." + date;
	}
	

}
