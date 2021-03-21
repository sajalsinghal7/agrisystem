package com.fms.farm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledJobs {

	@Autowired
	public UploadDegreeDayHandler uploadDegreeDayHandler;
	@Scheduled(fixedDelay = 300000)
	public void pollDegreeDayUploadJobAndProcess() {
		System.out.println("Statrt Degree Day Poller");
		uploadDegreeDayHandler.updateFirstSubmitted();
	}
}
