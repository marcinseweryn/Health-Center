package com.github.marcinseweryn.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marcinseweryn.model.Patient;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.EmailService;
import com.github.marcinseweryn.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {	
		
		return "main/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		
		
		return "main/login";
	}
	
	@RequestMapping(value = "/forgot-account", method = RequestMethod.GET)
	public String forgotPasswordFirstStepGet(){	
		
		return "main/forgot-account";
	}
	
	@RequestMapping(value = "/forgot-account", method = RequestMethod.POST)
	public String forgotPasswordFirstStepPost(@RequestParam String email, RedirectAttributes redirectAttributes){	
		
		String code = emailService.forgottenLoginDataFirstStep(email);

		redirectAttributes.addFlashAttribute("status", code);
		
		if(!code.equals("incorrectEmail")){
			
			redirectAttributes.addFlashAttribute("email", email);
			
			return "redirect:/forgot-account-2";
		}
				
		return "redirect:/forgot-account-information";
	}
	
	@RequestMapping(value = "/forgot-account-2", method = RequestMethod.GET)
	public String forgotPasswordSecondStepGet(@ModelAttribute("status") String code, Model model,
			@ModelAttribute("email") String email,  RedirectAttributes redirectAttributes){
	
		if(code.equals("")){ // block refresh
			return "redirect:/";
		}
		
		model.addAttribute("code", code);
		model.addAttribute("email", email);
		
		return "main/forgot-account-2";
	}
	
	
	@RequestMapping(value = "/forgot-account-2", method = RequestMethod.POST)
	public String forgotPasswordSecondStepPost(@RequestParam String email, @RequestParam String enteredCode,
			@RequestParam String code, RedirectAttributes redirectAttributes){
		
		String status;
		if(code.equals(enteredCode)){
			
			User user = userService.findUserByEmail(email);
			emailService.forgottenLoginDataSecondStep(email, user);
			status = "correctCode";
			
		}else{
			status = "incorrectCode";
		}
		
		redirectAttributes.addFlashAttribute("status", status);
		
		return "redirect:/forgot-account-information";
	}
	
	
	@RequestMapping(value = "/forgot-account-information", method = RequestMethod.GET)
	public String forgotAccountInformationGet(@ModelAttribute("status") String status, Model model){
		
		if(status.equals("")){	// block refresh
			return "redirect:/";
		}
		
		model.addAttribute("status", status);
		
		return "main/forgot-account-information";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model){
		
		model.addAttribute("patient", new Patient());

		return "main/registration";
	}
	
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
