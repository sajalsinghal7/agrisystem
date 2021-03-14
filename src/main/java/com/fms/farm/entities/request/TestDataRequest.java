package com.fms.farm.entities.request;

public class TestDataRequest {
	String id;
	String status;
	String createDate;
	String endDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public TestDataRequest(String id, String status, String createDate, String endDate) {
		super();
		this.id = id;
		this.status = status;
		this.createDate = createDate;
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "TestDataRequest [id=" + id + ", status=" + status + ", createDate=" + createDate + ", endDate="
				+ endDate + "]";
	}
	
}
