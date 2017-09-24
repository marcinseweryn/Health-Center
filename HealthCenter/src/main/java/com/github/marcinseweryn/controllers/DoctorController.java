package com.github.marcinseweryn.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.marcinseweryn.model.Doctor;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.DoctorService;
import com.github.marcinseweryn.service.UserService;

@Controller
public class DoctorController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@ModelAttribute("username")
	public String getUsername(Principal principal){
		String pesel = principal.getName();
		
		User user = userService.findUser(pesel);
	    
		String username = user.getName() + " " + user.getSurname();
	    return username;
	}
	
	@RequestMapping(value = "/doctor/home", method = RequestMethod.GET)
	public String home() {	
		
		return "doctor/home";
	}
	
	@RequestMapping(value = "/doctor/myAccount", method = RequestMethod.GET)
	public String myAccount(Model model, Principal principal) {
		String pesel = principal.getName();
	
		model.addAttribute("user",userService.findUser(pesel));
		
		Doctor doctor = new Doctor();
		doctor.setPesel(pesel);
		doctor = doctorService.findDoctors(doctor).get(0);
		model.addAttribute("doctor", doctor);
		
		return "doctor/myAccount";
	}
	
	@RequestMapping(value = "/doctor/myAccount", method = RequestMethod.POST)
	public String myAccountUpdate(User user,@RequestParam String spec1, @RequestParam String spec2, 
			@RequestParam String spec3,	@RequestParam String information,Principal principal){
		
		List<Integer> userID = new ArrayList<>();
		Integer pesel = Integer.parseInt(principal.getName());

		userID.add(pesel);	
		userService.updateUsers(userID, user);
		
		Doctor doctor =  new Doctor();
		doctor.setSpecialization_1(spec1);
		doctor.setSpecialization_2(spec2);
		doctor.setSpecialization_3(spec3);
		doctor.setInformation(information);
		
		doctorService.updateDoctorByID(doctor, pesel.toString());
		
		return "redirect:/doctor/myAccount";
	}
	
		

}
