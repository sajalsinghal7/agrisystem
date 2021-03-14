package com.fms.farm.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fms.farm.entities.request.TestDataRequest;
import com.fms.farm.entities.request.UploadTestFileRequest;
import com.fms.farm.service.UploadTestDataService;
@Service
public class FileStreamingUtility {
	@Autowired
	public UploadTestDataService uploadTestDataService;
	public void streamReadingFsSyncWritingDB(UploadTestFileRequest request) {
			String del = request.getDelimitter();
			Path path = Paths.get(request.getPath());
			try {
				Stream<String> line  = Files.lines(path).parallel();
				line.forEach(l -> createTestDataRequest(l, del));
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public void createTestDataRequest(String line, String del) {
//		System.out.println(line);
		String[] arr = line.split(del);
//		System.out.println(arr.length);
		uploadTestDataService.addDataToDynamoDB(new TestDataRequest(arr[0], arr[1], arr[2], arr[3]));
	}
}
