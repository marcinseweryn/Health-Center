package com.github.marcinseweryn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DoctorController {

	@RequestMapping(value = "/doctor/home", method = RequestMethod.GET)
	public String home() {	
		
		return "doctor/home";
	}
}
