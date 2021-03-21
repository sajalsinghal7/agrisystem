package com.fms.farm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fms.farm.dao.UploadDegreeDayJobDao;
import com.fms.farm.entities.DegreeDay;
import com.fms.farm.entities.request.UploadDegreeDay;
import com.fms.farm.entities.response.DegreeDayUploadStatus;
import com.fms.farm.util.FormattedDateGenerator;
import com.fms.farm.util.IdGenerator;
import com.fms.farm.util.UploadJobStatus;

@Service
public class UploadDegreeDayHandler {

	@Autowired
	public DynamoDBMapper dbMapper;
	
	public DegreeDayUploadStatus createUploadRequest(UploadDegreeDay degreeDay) {
		UploadDegreeDayJobDao dao =new UploadDegreeDayJobDao(IdGenerator.generateId(), FormattedDateGenerator.getDateTimeNow(), FormattedDateGenerator.getDateTimeNow(), UploadJobStatus.SUBMITTED.name(), degreeDay.toString(), degreeDay.getPath(), degreeDay.getDelimitter(), degreeDay.getRegion());
		dbMapper.save(dao);
		return new DegreeDayUploadStatus(dao.getId(), dao.getCreateDate(), dao.getUpdateDate(), dao.getStatus(), degreeDay.getPath(), degreeDay.getDelimitter(), degreeDay.getRegion());
	}
	
	
}
