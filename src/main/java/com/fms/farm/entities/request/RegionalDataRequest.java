package com.fms.farm.entities.request;

public class RegionalDataRequest {
	String region;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public RegionalDataRequest(String region) {
		super();
		this.region = region;
	}
	
}
