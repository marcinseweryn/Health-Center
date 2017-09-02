package com.github.marcinseweryn.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.DoctorService;
import com.github.marcinseweryn.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DoctorService doctorService;
	
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
	public String myAccount(@ModelAttribute("username") String username, Model model, Principal principal) {
		String pesel = principal.getName();
	
		model.addAttribute("user",userService.findUser(pesel));
		
		return "user/myAccount";
	}
	
	@RequestMapping(value = "/user/myAccount", method = RequestMethod.POST)
	public String myAccountUpdate(User user, Principal principal, BindingResult bindingResult){
		List<Integer> userID = new ArrayList<>();
		Integer pesel = Integer.parseInt(principal.getName());

		userID.add(pesel);	
		userService.updateUsers(userID, user);
		
		return "redirect:/user/myAccount";
	}
	
	@RequestMapping(value = "/user/registration-to", method = RequestMethod.GET)
	public String registrationTo(@ModelAttribute("username") String username) {	
		
	    
		return "user/registration-to";
	}
	
	@RequestMapping(value = "/user/registration-to", method = RequestMethod.POST)
	public String registerTo(@ModelAttribute("username") String username, @RequestParam String specialization
			 ,RedirectAttributes redirectAttributes) {	

	    redirectAttributes.addFlashAttribute("specialization", specialization);
		
		return "redirect:/user/registration-doctor";
	}
	
	@RequestMapping(value = "/user/registration-doctor", method = RequestMethod.GET)
	public String registrationDoctor(Model model, @ModelAttribute("username") String username,
			@ModelAttribute("specialization") String specialization) {	
		
		System.out.println(doctorService.findDoctorsIDsBySpecialization(specialization).toString());
		
		model.addAttribute("doctors", userService.findUsersByIDs(doctorService.findDoctorsIDsBySpecialization(specialization)));
		
		return "user/registration-doctor";
	}

}
