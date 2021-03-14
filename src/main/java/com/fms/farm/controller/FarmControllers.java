package com.fms.farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.synchronoss.cloud.nio.stream.storage.FileStreamStorage;

import com.fms.farm.entities.Hello;
import com.fms.farm.entities.request.TestDataRequest;
import com.fms.farm.entities.request.UploadTestFileRequest;
import com.fms.farm.service.UploadTestDataService;
import com.fms.farm.util.FileStreamingUtility;

@RestController
@RequestMapping(value = "api/v1")
public class FarmControllers {
	@Autowired
	public UploadTestDataService uploadTestDataService;
	@Autowired
	public FileStreamingUtility fileStreamingUtility;
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
	
}
