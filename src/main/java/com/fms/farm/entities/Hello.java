package com.fms.farm.entities;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

public class Hello extends RepresentationModel<EntityModel<Hello>> {
	String hello = "Hi Digital Farmers";

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	@Override
	public String toString() {
		return "Hello [hello=" + hello + "]";
	}
	
	
}
