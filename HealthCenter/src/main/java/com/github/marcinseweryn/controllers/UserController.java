package com.github.marcinseweryn.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("username")
	public String getUsername(Principal principal){
		String pesel = principal.getName();
		
		User user = userService.findUser(pesel);
	    
		String username = user.getName() + " " + user.getSurname();
	    return username;
	}
	
	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String home(@ModelAttribute("username") String username) {	
		
	    
		return "user/home";
	}
	
	@RequestMapping(value = "/user/myAccount", method = RequestMethod.GET)
	public String myAccount(@ModelAttribute("username") String username) {
		
		return "user/myAccount";
	}

}
