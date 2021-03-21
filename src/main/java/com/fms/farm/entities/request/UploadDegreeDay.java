package com.fms.farm.entities.request;

public class UploadDegreeDay {
	String path;
	Character delimitter;
	String region;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Character getDelimitter() {
		return delimitter;
	}
	public void setDelimitter(Character delimitter) {
		this.delimitter = delimitter;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public UploadDegreeDay(String path, Character delimitter, String region) {
		super();
		this.path = path;
		this.delimitter = delimitter;
		this.region = region;
	}
	@Override
	public String toString() {
		return "UploadDegreeDay [path=" + path + ", delimitter=" + delimitter + ", region=" + region + "]";
	}
	
	
}
