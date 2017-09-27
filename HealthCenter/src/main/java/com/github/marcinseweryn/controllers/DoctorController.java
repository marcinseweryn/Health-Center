package com.github.marcinseweryn.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.ejb.criteria.expression.function.CurrentTimestampFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marcinseweryn.model.Doctor;
import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.Presence;
import com.github.marcinseweryn.service.DoctorService;
import com.github.marcinseweryn.service.DutyService;
import com.github.marcinseweryn.service.UserService;
import com.github.marcinseweryn.service.VisitService;

@Controller
public class DoctorController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private DutyService dutyService;
	
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
	
	
	@RequestMapping(value = "/doctor/visits", method = RequestMethod.GET)
	public String visits(Model model, Principal principal){
		String pesel = principal.getName();
		Boolean lackOfDuty = false, lackOfNextVisit = false, start = true;
		Timestamp currentTime =  new Timestamp(System.currentTimeMillis());
		Duty duty;
		Visit nextVisit;
				
		try{				
			duty = dutyService.findDutyForDoctorVisitsByDoctorID(pesel).get(0);
		}catch(IndexOutOfBoundsException e){	// if lack of duty
			lackOfDuty = true;
			return "doctor/visits";
		}finally {
			model.addAttribute("lackOfDuty", lackOfDuty);
		}
		
		// lock start before work time
		if(dutyService.findDutyByID(duty.getID()).getDate().after(currentTime)){
			start = false;
		}
		model.addAttribute("start", start);
		
		try{
			nextVisit = visitService.getCurrentQueueByDutyID(duty.getID()).get(0);
		}catch(IndexOutOfBoundsException e){	// if lack of patients
			lackOfNextVisit = true;
			return "doctor/visits";
		}finally {
			model.addAttribute("lackOfNextVisit", lackOfNextVisit);
			
			model.addAttribute("duty", duty);
			model.addAttribute("visitList", visitService.findVisitForQueue(duty.getID()));
		}

		User nextPatient = userService.findUser(nextVisit.getPatientPesel());
		model.addAttribute("nextPatient", nextPatient);
		model.addAttribute("positionInQueue", nextVisit.getPositionInQueue());
		
		return "doctor/visits";
	}
	
	@RequestMapping(value = "/doctor/visits", method = RequestMethod.POST)
	public String visitsPost(@RequestParam String action, @RequestParam Integer dutyID, 
				RedirectAttributes redirectAttributes){
		
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		
		if(action.equals("present")){
			Visit nextVisit = visitService.getCurrentQueueByDutyID(dutyID).get(0);
			Integer visitID  = nextVisit.getID();
			
			visitService.updatePresence(Presence.current.getValue(), visitID);
			redirectAttributes.addFlashAttribute("visitID", visitID);
			return "redirect:/doctor/patient-card";
			
		}else if(action.equals("absent")){
			Visit nextVisit = visitService.getCurrentQueueByDutyID(dutyID).get(0);
			Integer visitID  = nextVisit.getID();
			
			if(nextVisit.getPresence() != Presence.firstAbsent.getValue()){
				visitService.updatePresence(Presence.firstAbsent.getValue(), visitID);
			}else{
				visitService.updatePresence(Presence.absent.getValue(), visitID);
			}	
		}else if(action.equals("start")){
			// lock start before work time
			if(dutyService.findDutyByID(dutyID).getDate().after(currentTime)){
			}else{
				dutyService.updateStartDateByID(dutyID);
			}
			
		}else if(action.equals("end")){
			dutyService.updateEndDateByID(dutyID);
		}
		
		return "redirect:/doctor/visits";
	}
	
	@RequestMapping(value = "/doctor/patient-card", method = RequestMethod.GET)
	public String patientCard(@ModelAttribute("visitID") Integer visitID, Model model){
		
		model.addAttribute("visitID", visitID);
		
		return "doctor/patient-card";
	}
	
	@RequestMapping(value = "/doctor/patient-card", method = RequestMethod.POST)
	public String patientCardPost(@RequestParam Integer visitID){
		
		visitService.updatePresence(Presence.present.getValue(), visitID);
		
		return "redirect:/doctor/visits";
	}
		

}
