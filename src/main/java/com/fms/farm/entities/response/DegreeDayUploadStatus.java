package com.fms.farm.entities.response;

import java.util.Date;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import com.fms.farm.util.UploadJobStatus;

public class DegreeDayUploadStatus extends RepresentationModel<EntityModel<DegreeDayUploadStatus>> {

	String id;
	String createDate;
	String updateDate;
	String jobStatus;
	String path;
	Character delimitter;
	String region;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
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
	public DegreeDayUploadStatus(String id, String createDate, String updateDate, String jobStatus, String path,
			Character delimitter, String region) {
		super();
		this.id = id;
		
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.jobStatus = jobStatus;
		this.path = path;
		this.delimitter = delimitter;
		this.region = region;
	}



	
	
}
