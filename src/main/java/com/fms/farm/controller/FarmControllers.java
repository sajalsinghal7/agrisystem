package com.fms.farm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fms.farm.entities.Hello;

@RestController
@RequestMapping(value = "api/v1")
public class FarmControllers {
	@RequestMapping(value = ControllerLinks.HELLO_FARM, method = RequestMethod.GET)
	public Hello helloDigitalFarmer() {
		return new Hello();
	}
}
