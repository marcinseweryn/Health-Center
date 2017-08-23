package com.github.marcinseweryn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.marcinseweryn.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/admin/home/aa", method = RequestMethod.GET)
	public String home() {	
		
		return "admin/home";
	}
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String userManagment(Model model) {	
		
		model.addAttribute("users",userService.findAllUsers());
		
		return "admin/usersManagment";
	}
	
}
