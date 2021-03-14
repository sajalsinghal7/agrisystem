package com.fms.farm.entities.request;

public class UploadTestFileRequest {

	String path;
	String delimitter;
	
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
	public UploadTestFileRequest(String path, String delimitter) {
		super();
		this.path = path;
		this.delimitter = delimitter;
	}
	
	
}
