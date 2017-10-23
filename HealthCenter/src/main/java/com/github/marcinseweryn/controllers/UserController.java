package com.github.marcinseweryn.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marcinseweryn.model.Doctor;
import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.Patient;
import com.github.marcinseweryn.model.PatientHistory;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.pojo.DateFromTo;
import com.github.marcinseweryn.pojo.DutyDetailsForPatientQueue;
import com.github.marcinseweryn.pojo.PatientVisitDetails;
import com.github.marcinseweryn.service.DoctorService;
import com.github.marcinseweryn.service.DutyService;
import com.github.marcinseweryn.service.PatientHistoryService;
import com.github.marcinseweryn.service.PatientService;
import com.github.marcinseweryn.service.UserService;
import com.github.marcinseweryn.service.VisitService;
import com.github.marcinseweryn.service.WorkScheduleService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private WorkScheduleService workScheduleService;
	
	@Autowired
	private DutyService dutyService;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PatientHistoryService patientHistoryService;
	
	
	@ModelAttribute("username")
	public String getUsername(Principal principal){
		Integer ID = Integer.parseInt(principal.getName());
		
		User user = userService.findUserByID(ID);
	    
		String username = user.getName() + " " + user.getSurname();
	    return username;
	}
	
	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String home() {	
		
	    
		return "user/home";
	}
	
	@RequestMapping(value = "/user/myAccount", method = RequestMethod.GET)
	public String myAccount(Model model, Principal principal) {
		Integer ID = Integer.parseInt(principal.getName());
	
		model.addAttribute("patient",userService.findUserByID(ID));
		
		return "user/myAccount";
	}
	
	@RequestMapping(value = "/user/myAccount", method = RequestMethod.POST)
	public String myAccountUpdate(Patient patient, Principal principal, BindingResult bindingResult){
		Integer ID = Integer.parseInt(principal.getName());
	
		patientService.updatePatient(ID, patient);
		
		return "redirect:/user/myAccount";
	}
	
	@RequestMapping(value = "/user/registration-to", method = RequestMethod.GET)
	public String registrationTo() {	
		
	    
		return "user/registration-to";
	}
	
	@RequestMapping(value = "/user/registration-to", method = RequestMethod.POST)
	public String registerTo(@RequestParam String specialization
			 ,RedirectAttributes redirectAttributes) {	

	    redirectAttributes.addFlashAttribute("specialization", specialization);
		
		return "redirect:/user/registration-doctor";
	}
	
	@RequestMapping(value = "/user/registration-doctor", method = RequestMethod.GET)
	public String registrationDoctor(Model model, @ModelAttribute("specialization") String specialization) {	

		if(!specialization.equals("")){
			model.addAttribute("weekWorkSchedule", workScheduleService.findWeekWorkScheduleByIDs(doctorService.findDoctorsIDsBySpecialization(specialization)));
			return "user/registration-doctor";
		}else{
			return "user/registration-to";
		}
	}
	
	@RequestMapping(value = "/user/registration-doctor", method = RequestMethod.POST)
	public String registerToDoctor(@RequestParam String doctorID, RedirectAttributes redirectAttributes){
		
		redirectAttributes.addFlashAttribute("doctorID", doctorID);
		
		return "redirect:/user/registration-date";
	}
	
	@RequestMapping(value = "/user/registration-date", method = RequestMethod.GET)
	public String registrationDate(@ModelAttribute("doctorID") String docID, Model model, Principal principal){
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Integer patientID = Integer.parseInt(principal.getName());
		Boolean alreadyRegistered = false;
		
		if(!docID.equals("")){
			Integer doctorID = Integer.parseInt(docID);
			WorkSchedule schedule = new WorkSchedule();
			Duty duty = new Duty();
			schedule.setDoctorID(doctorID);
			duty.setDoctorID(doctorID);
			duty.setDate(currentDate);
			List<Duty> dutyList =  dutyService.findDutyForAdd(duty);
			
			while(dutyList.size() <  6){
				dutyService.addDuty(workScheduleService.findSchedules(schedule), dutyList);
				dutyList =  dutyService.findDutyForAdd(duty);
			}
			model.addAttribute("dutyList",dutyList);
			
			Integer iterStat = 1;
			for(Duty duty1 : dutyList){	
				model.addAttribute("visitList" + iterStat, visitService.findVisitForDoctorByDutyID(duty1.getID()));
				
				List<Visit> visitList = visitService.findVisitForDoctorByDutyID(duty1.getID());
				if(alreadyRegistered == false){
					for(Visit visit : visitList){
						if(visit.getPatientID() == patientID){
							alreadyRegistered = true;
							model.addAttribute("registrationDate",duty1.getDate());
							model.addAttribute("position",visit.getPositionInQueue());
							break;
						}
					}
				}
				iterStat++;
			}
			model.addAttribute("alreadyRegistered",alreadyRegistered);
			return "user/registration-date";
		}else{
			return "user/registration-to";
		}
		
	}
	
	@RequestMapping(value = "/user/registration-date", method = RequestMethod.POST)
	public String registerDate(Principal principal, @RequestParam Integer dutyID,  RedirectAttributes redirectAttributes){
		Integer patientID = Integer.parseInt(principal.getName());
		Integer positionInQueue = visitService.findVisitForDoctorByDutyID(dutyID).size() + 1;
		
		visitService.addVisit(dutyID, patientID, positionInQueue);
		
		redirectAttributes.addFlashAttribute("positionInQueue",positionInQueue);
		redirectAttributes.addFlashAttribute("dutyID", dutyID);
		
		return "redirect:/user/registration-completed";
	}
	
	@RequestMapping(value = "/user/registration-completed", method = RequestMethod.GET)
	public String registrationCompleted(Model model, @ModelAttribute("dutyID") Integer dutyID, @ModelAttribute("positionInQueue") Integer positionInQueue){
		Duty duty = dutyService.findDutyByID(dutyID);
		
		Doctor doctor = new Doctor();
		doctor.setID(duty.getDoctorID());
		List<Doctor> doctorList = doctorService.findDoctors(doctor);
		
		String doctorName = doctorList.get(0).getName();
		String doctorSurname = doctorList.get(0).getSurname();
		
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("doctorSurname", doctorSurname);
		model.addAttribute("positionInQueue", positionInQueue);
		model.addAttribute("date",duty.getDate());
		
		return "user/registration-completed";
	}
	
	
	@RequestMapping(value = "/user/visits", method = RequestMethod.GET)
	public String visits(Model model, Principal principal){
		Integer ID = Integer.parseInt(principal.getName());
		List<PatientVisitDetails> patientVisitDetailsList = visitService.findVisitDetailsForPatientByPatientID(ID);
		
		model.addAttribute("patientVisitDetailsList", patientVisitDetailsList);
		
		return "user/visits";
	}
	
	
	@RequestMapping(value = "/user/visits", method = RequestMethod.POST)
	public String visitsPost(@RequestParam("visitID") Integer visitID, @RequestParam("dutyID") Integer dutyID){

		visitService.deleteVisitByID(visitID, dutyID);
		return "redirect:/user/visits";
	}
	
	@RequestMapping(value = "/user/queue", method = RequestMethod.GET)
	public String queue(Model model, Principal principal){
		Integer ID = Integer.parseInt(principal.getName());
		DutyDetailsForPatientQueue dutyDetails = null;
		boolean lackOfDuty = false;
		
		try{
			dutyDetails =  dutyService.findDutyDetailsForCurrentDayByPatientID(ID).get(0);
		}catch(IndexOutOfBoundsException e){
			lackOfDuty = true;
			return "user/queue";		
		}finally {
			model.addAttribute("lackOfDuty",lackOfDuty);
		}
		
		model.addAttribute("dutyDetails",dutyDetails);
		model.addAttribute("visitsList", visitService.findVisitForQueueByDutyID(dutyDetails.getDutyID()));
		model.addAttribute("positionInQueue", visitService.findVisitDetailsForPatientByPatientID(ID).get(0).getPositionInQueue());
		return "user/queue";
	}
	
	
	@RequestMapping(value = "/user/history", method = RequestMethod.GET)
	public String historyGet( Model model, @ModelAttribute("dateFromTo") DateFromTo dateFromTo, Principal principal){
		
		Integer patientID = Integer.parseInt(principal.getName());
		
		List<PatientHistory> list = patientHistoryService.findPatientHistoryByDateAndPatientID(dateFromTo, patientID);
		
		model.addAttribute("patientHistoryList", list);
		model.addAttribute("dateFromTo", dateFromTo);
		
		return "user/history";
	}
	
	@RequestMapping(value = "/user/history", method = RequestMethod.POST)
	public String patientHistoryPost(DateFromTo dateFromTo, RedirectAttributes redirectAttributes){
				
		redirectAttributes.addFlashAttribute("dateFromTo", dateFromTo);
	
		return "redirect:/user/history";
	}
	
}
