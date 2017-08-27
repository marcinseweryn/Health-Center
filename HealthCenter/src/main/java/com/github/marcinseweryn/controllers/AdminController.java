package com.github.marcinseweryn.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.marcinseweryn.model.IDsList;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String home() {	
		
		return "admin/home";
	}
	
	@RequestMapping(value = "/admin/usersManagment", method = RequestMethod.GET)
	public String userManagment(Model model) {	
		
		model.addAttribute("users", userService.findAllUsers());
		model.addAttribute("user", new User());
		model.addAttribute("list", new IDsList());
		
		return "admin/usersManagment";
	}
	
	@RequestMapping(value = "/admin/usersManagment", method = RequestMethod.POST)
	public String userManagmentDelete(IDsList list, BindingResult bindingResult) {	
		
		userService.deleteUsers(list.getUsersIDs());
		
		return "redirect:/admin/usersManagment";
	}
	
}
