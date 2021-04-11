package com.fms.farm.service;

import com.fms.farm.entities.request.FileUploadRequest;

public interface ModelHandler {
	public String uploadModel(FileUploadRequest request);
	public void streamReadingFsSyncWritingDB(String pathRequest, String delimitter, String region);
	public void parseObjectAndUploadToDB(String line, String del, String region);
}
