package com.github.marcinseweryn.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marcinseweryn.model.Doctor;
import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.Patient;
import com.github.marcinseweryn.model.PatientCard;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.Presence;
import com.github.marcinseweryn.service.DoctorService;
import com.github.marcinseweryn.service.DutyService;
import com.github.marcinseweryn.service.PatientCardService;
import com.github.marcinseweryn.service.PatientService;
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
	
	@Autowired
	private PatientCardService patientCardService;
	
	@Autowired 
	private PatientService patientService;
	
	@ModelAttribute("username")
	public String getUsername(Principal principal){
		Integer ID = Integer.parseInt(principal.getName());
		
		User user = userService.findUserByID(ID);
	    
		String username = user.getName() + " " + user.getSurname();
	    return username;
	}
	
	@RequestMapping(value = "/doctor/home", method = RequestMethod.GET)
	public String home() {	
		
		return "doctor/home";
	}
	
	@RequestMapping(value = "/doctor/myAccount", method = RequestMethod.GET)
	public String myAccount(Model model, Principal principal) {
		Integer ID = Integer.parseInt(principal.getName());
		
		Doctor doctor = new Doctor();
		doctor = doctorService.findDoctorByID(ID);
		model.addAttribute("doctor", doctor);
		
		return "doctor/myAccount";
	}
	
	@RequestMapping(value = "/doctor/myAccount", method = RequestMethod.POST)
	public String myAccountUpdate(Doctor doctor, Principal principal){
		
		List<Integer> userIDs = new ArrayList<>();
		Integer doctorID = Integer.parseInt(principal.getName());

		userIDs.add(doctorID);	
		userService.updateUsers(userIDs, doctor);
		
		doctorService.updateDoctorByID(doctor, doctorID);
		
		return "redirect:/doctor/myAccount";
	}
	
	
	@RequestMapping(value = "/doctor/visits", method = RequestMethod.GET)
	public String visits(Model model, Principal principal){
		Integer ID = Integer.parseInt(principal.getName());
		Boolean lackOfDuty = false, lackOfNextVisit = false, start = true;
		Timestamp currentTime =  new Timestamp(System.currentTimeMillis());
		Duty duty;
		Visit nextVisit;
				
		try{				
			duty = dutyService.findDutyForDoctorVisitsByDoctorID(ID).get(0);
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
			model.addAttribute("visitList", visitService.findVisitForQueueByDutyID(duty.getID()));
		}

		User nextPatient = userService.findUserByID(nextVisit.getPatientID());
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
		
		Visit visit = visitService.findVisitByID(visitID);
		Patient patient = (Patient) userService.findUserByID(visit.getPatientID());
		
		model.addAttribute("visitID", visit.getID());
		model.addAttribute("patient", patient);
		model.addAttribute("patientCard", new PatientCard());
		
		return "doctor/patient-card";
	}
	
	@RequestMapping(value = "/doctor/patient-card", method = RequestMethod.POST)
	public String patientCardPost(@RequestParam String action, @RequestParam Integer visitID, 
			Patient patient, PatientCard patientCard, RedirectAttributes redirectAttributes){
		
		if(action.equals("patient-served")){
			visitService.updatePresence(Presence.present.getValue(), visitID);
			patientCardService.addPatientCard(patientCard);
			
		}else if(action.equals("save")){			
			patientService.updatePatient(patient.getID(), patient);
			redirectAttributes.addFlashAttribute("visitID", visitID);
			return "redirect:/doctor/patient-card";
			
		}else if(action.equals("history")){
			
			
		}	
		return "redirect:/doctor/visits";
	}
		

}
