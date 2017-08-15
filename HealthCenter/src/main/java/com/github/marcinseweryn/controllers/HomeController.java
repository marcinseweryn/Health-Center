package com.github.marcinseweryn.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.UserService;


@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {	
		
		return "main/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		
		return "main/login";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model){
		
		model.addAttribute("user", new User());

		return "main/registration";
	}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@Valid User user, BindingResult  bindingResult){
		
		userService.addUser(user);
		
		return "redirect:/user/home";
	}
	
	
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(){
		
		return "main/information";
	}
	
	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public String aboutUs(){
		
		return "main/aboutUs";
	}
	
	
}
