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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.service.DoctorService;
import com.github.marcinseweryn.service.DutyService;
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
	
	@ModelAttribute("username")
	public String getUsername(Principal principal){
		String pesel = principal.getName();
		
		User user = userService.findUser(pesel);
	    
		String username = user.getName() + " " + user.getSurname();
	    return username;
	}
	
	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String home() {	
		
	    
		return "user/home";
	}
	
	@RequestMapping(value = "/user/myAccount", method = RequestMethod.GET)
	public String myAccount(Model model, Principal principal) {
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
	public String registrationDate(@ModelAttribute("doctorID") String doctorID, Model model){
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());

		if(!doctorID.equals("")){
			WorkSchedule schedule = new WorkSchedule();
			Duty duty = new Duty();
			schedule.setPesel(doctorID);
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
				iterStat++;
			}
			
			return "user/registration-date";
		}else{
			return "user/registration-to";
		}
		
	}
	
	@RequestMapping(value = "/user/registration-date", method = RequestMethod.POST)
	public String registerDate(Principal principal, @RequestParam Integer dutyID){
		String pesel = principal.getName();
		Integer positionInQueue = visitService.findVisitForDoctorByDutyID(dutyID).size() + 1;
		
		visitService.addVisit(dutyID, pesel, positionInQueue);
		
		return "user/home";
	}
	
}
