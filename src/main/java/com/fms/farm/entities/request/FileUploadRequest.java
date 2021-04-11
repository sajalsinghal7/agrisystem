package com.fms.farm.entities.request;

public class FileUploadRequest {
	String path;
	String delimitter;
	String region;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDelimitter() {
		return delimitter;
	}
	public void setDelimitter(String delimitter) {
		this.delimitter = delimitter;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "FileUplaodRequest [path=" + path + ", delimitter=" + delimitter + ", region=" + region + "]";
	}
	public FileUploadRequest(String path, String delimitter, String region) {
		super();
		this.path = path;
		this.delimitter = delimitter;
		this.region = region;
	}
	
}
