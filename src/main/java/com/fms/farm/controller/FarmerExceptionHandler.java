package com.fms.farm.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FarmerExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	 public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
		e.printStackTrace();
		 Map<String, Object> body = new HashMap<String, Object>();
		 body.put("timestamp", LocalDate.now());
		 body.put("message", "Sorry Digital Farmer Something went wrong");
		 return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	 }
}
