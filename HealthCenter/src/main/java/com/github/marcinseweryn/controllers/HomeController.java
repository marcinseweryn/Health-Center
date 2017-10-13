package com.github.marcinseweryn.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marcinseweryn.model.Patient;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.UserService;


@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {	
		
		return "main/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		
		
		return "main/login";
	}
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model){
		
		model.addAttribute("patient", new Patient());

		return "main/registration";
	}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@Valid Patient patient, RedirectAttributes redirectAttributes){
		
		patient.setRole("ROLE_PATIENT");
		userService.addUser(patient);
		
		User user = userService.findUsers(patient).get(0);
		String ID = user.getID().toString();
		redirectAttributes.addFlashAttribute("accountNumber", ID);
		
		return "redirect:/registration-completed";
	}
	
	@RequestMapping(value = "/registration-completed", method = RequestMethod.GET)
	public String registrationCompleted(Model model, @ModelAttribute("accountNumber") String ID){

		if(!ID.equals("")){
			model.addAttribute("accountNumber", ID);
			return "main/registration-completed";
		}
		return "main/home";
	}
	
	
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(){
		
		return "main/information";
	}
	
	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public String aboutUs(){
		
		return "main/aboutUs";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String error403(){
		
		return "errors/403";
	}
	
	
}
