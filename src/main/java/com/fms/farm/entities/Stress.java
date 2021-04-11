package com.fms.farm.entities;

public class Stress {

	String id;
	Integer date;
	Double eto;
	Double eta;
	Double etc;
	String regionOfAnalysis;
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
	public Double getEto() {
		return eto;
	}
	public void setEto(Double eto) {
		this.eto = eto;
	}
	public Double getEta() {
		return eta;
	}
	public void setEta(Double eta) {
		this.eta = eta;
	}
	public Double getEtc() {
		return etc;
	}
	public void setEtc(Double etc) {
		this.etc = etc;
	}
	public String getRegionOfAnalysis() {
		return regionOfAnalysis;
	}
	public void setRegionOfAnalysis(String regionOfAnalysis) {
		this.regionOfAnalysis = regionOfAnalysis;
	}
	public Stress(String id, Integer date, Double eto, Double eta, Double etc, String regionOfAnalysis) {
		super();
		this.id = id;
		this.date = date;
		this.eto = eto;
		this.eta = eta;
		this.etc = etc;
		this.regionOfAnalysis = regionOfAnalysis;
	}


	
}
