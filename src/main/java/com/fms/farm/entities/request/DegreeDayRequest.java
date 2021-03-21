package com.fms.farm.entities.request;

public class DegreeDayRequest {

	String from;
	String to;
	String region;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String location) {
		this.region = location;
	}
	public DegreeDayRequest(String from, String to, String region) {
		super();
		this.from = from;
		this.to = to;
		this.region = region;
	}
	
}
