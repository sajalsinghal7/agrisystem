package com.fms.farm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.synchronoss.cloud.nio.stream.storage.FileStreamStorage;

import com.fms.farm.dao.UploadJobStatusDynamoDb;
import com.fms.farm.entities.Hello;
import com.fms.farm.entities.request.TestDataRequest;
import com.fms.farm.entities.request.UploadTestFileRequest;
import com.fms.farm.service.FetchJobStatusDataFromDB;
import com.fms.farm.service.UploadTestDataService;
import com.fms.farm.util.FileStreamingUtility;

@RestController
@RequestMapping(value = "api/v1")
public class FarmControllers {
	@Autowired
	public UploadTestDataService uploadTestDataService;
	@Autowired
	public FileStreamingUtility fileStreamingUtility;
	
	@Autowired
	public FetchJobStatusDataFromDB fetchJobStatusDataFromDB;

	@RequestMapping(value = ControllerLinks.HELLO_FARM, method = RequestMethod.GET)
	public Hello helloDigitalFarmer() {
		return new Hello();
	}
	
	@RequestMapping(value = ControllerLinks.ADD_TEST_DATA_VIA_API, method = RequestMethod.POST)
	public String addTestDataToDbViaAPI(@RequestBody TestDataRequest dataRequest) {
		uploadTestDataService.addDataToDynamoDB(dataRequest);
		return "Data Has Been Added to db";
	}
	
	@RequestMapping(value = ControllerLinks.UPLOAD_TEST_CSV_DATA_VIA_API, method = RequestMethod.POST)
	public String saveTestCSVFileDataToDB(@RequestBody UploadTestFileRequest request) {
//		FileStreamingUtility fileStreamingUtility = new FileStreamingUtility();
		fileStreamingUtility.streamReadingFsSyncWritingDB(request);
		return "Data Has Been Added to db";
	}
	
	@RequestMapping(value = ControllerLinks.UPLOAD_JOBS, method = RequestMethod.GET)
	public List<UploadJobStatusDynamoDb> getAllUploadJobs() {
		List<UploadJobStatusDynamoDb> list = null;
		try {
			list = fetchJobStatusDataFromDB.getAll();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
}
