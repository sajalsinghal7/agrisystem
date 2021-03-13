package com.fms.farm.entities.request;

import java.util.Arrays;

public class UploadFileToDbRequest {
	String[] schema;
	String filePath;
	public String[] getSchema() {
		return schema;
	}
	public void setSchema(String[] schema) {
		this.schema = schema;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "UploadFileToDb [schema=" + Arrays.toString(schema) + ", filePath=" + filePath + "]";
	}
	
}
