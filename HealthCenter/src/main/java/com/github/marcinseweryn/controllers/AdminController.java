package com.github.marcinseweryn.controllers;

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

import com.github.marcinseweryn.model.Doctor;
import com.github.marcinseweryn.model.Patient;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.pojo.IDsList;
import com.github.marcinseweryn.service.DoctorService;
import com.github.marcinseweryn.service.NewsService;
import com.github.marcinseweryn.service.UserService;
import com.github.marcinseweryn.service.WorkScheduleService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private WorkScheduleService workScheduleService;
	
	@Autowired
	private NewsService newsService;
	
	private List<User> foundUsersList = null;

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String home() {	
		
		return "admin/home";
	}
	
	@RequestMapping(value = "/admin/usersManagement", method = RequestMethod.GET)
	public String userManagement(Model model) {	
		
		if(foundUsersList != null){
			System.out.println(foundUsersList.size());
			model.addAttribute("users", foundUsersList);
			foundUsersList = null;
		}else{
			model.addAttribute("users", userService.findAllUsers());
		}
				
		model.addAttribute("user", new User());
		model.addAttribute("list", new IDsList());
		
		return "admin/usersManagment";
	}
	
	@RequestMapping(value = "/admin/usersManagement/update", method = RequestMethod.POST)
	public String userManagementupdate(@RequestParam String action,@RequestParam List<Integer> IDsList, User user, BindingResult bindingResult) {	

		if(action.equals("create")){
			
			if(user.getRole().equals("ROLE_DOCTOR")){
				Doctor doc = new Doctor();
				Doctor doctor = doc.downcastUser(user);

				userService.addUser(doctor);
			}else if(user.getRole().equals("ROLE_PATIENT")){
				Patient p = new Patient();
				Patient patient = p.downcastUser(user);
				
				userService.addUser(patient);
			}else{
				userService.addUser(user);
			}
			
		}else if(action.equals("delete")){
			
			userService.deleteUsers(IDsList);
			
		}else if(action.equals("update")){
			
			userService.updateUsers(IDsList, user);
			
		}else if(action.equals("search")){
			
			foundUsersList = userService.findUsers(user);
		}
		return "redirect:/admin/usersManagement";
	}
	
	@RequestMapping(value = "/admin/workSchedule", method = RequestMethod.GET)
	public String workSchedule(Model model, @ModelAttribute("searchDoctor") Doctor doctor, @ModelAttribute("searchSchedule") WorkSchedule schedule) {	
		
		model.addAttribute("allDoctors", doctorService.findDoctors(doctor));
		model.addAttribute("workSchedule",new WorkSchedule());
		model.addAttribute("doctor", new Doctor());	
		model.addAttribute("workSchedules", workScheduleService.findSchedules(schedule));
		model.addAttribute("list", new IDsList());
		
		return "admin/workSchedule";
	}
	
	@RequestMapping(value = "/admin/workSchedule", method = RequestMethod.POST)
	public String workScheduleUpdate(WorkSchedule schedule, Model model,@RequestParam String action,
			@RequestParam List<Integer> IDsList, RedirectAttributes redirectAttributes) {	
		
		if(action.equals("create")){
			
			workScheduleService.addSchedule(schedule);
			
		}else if(action.equals("delete")){
			
			workScheduleService.deleteSchedules(IDsList);
			
		}else if(action.equals("update")){
			
			workScheduleService.updateSchedules(schedule, IDsList);
			
		}else if(action.equals("search")){
			
			redirectAttributes.addFlashAttribute("searchSchedule", schedule);
		}	
		
		return "redirect:/admin/workSchedule";
	}
	
	@RequestMapping(value = "/admin/workSchedule/search", method = RequestMethod.POST)
	public String workScheduleSearch(Doctor doctor, Model model,RedirectAttributes redirectAttributes) {	
		
		doctor.setSpecialization_2(doctor.getSpecialization_1());
		doctor.setSpecialization_3(doctor.getSpecialization_1());
		redirectAttributes.addFlashAttribute("searchDoctor", doctor);
		
		return "redirect:/admin/workSchedule";
	} 
	
	@RequestMapping(value = "/admin/news-management", method = RequestMethod.GET)
	public String newsManagement(Model model){
		
		model.addAttribute("allNews",newsService.getAllNews());
		
		return "admin/news-management";
	}
	
	@RequestMapping(value = "/admin/news-management-update", method = RequestMethod.POST)
	public String newsManagementUpdateNews(@RequestParam String actionAndID, RedirectAttributes redirectAttributes){
		
		Integer ID = Integer.parseInt(actionAndID.substring(1, actionAndID.length()));
		Character action = actionAndID.charAt(0);

		redirectAttributes.addFlashAttribute("ID",ID);
		if(action == 'U'){
			return "redirect:/admin/news-management/news-update";
		}else{
			newsService.deleteNewsByID(ID);
			return "redirect:/admin/news-management";
		}
	}
	
	@RequestMapping(value = "/admin/news-management/news-update", method = RequestMethod.GET)
	public String newsUpdateGet(Model model){
		
		return "admin/news-update";
	}
	
	@RequestMapping(value = "/admin/add-news", method = RequestMethod.GET)
	public String addNews(Model model){
		
		return "admin/add-news";
	}
	
}
