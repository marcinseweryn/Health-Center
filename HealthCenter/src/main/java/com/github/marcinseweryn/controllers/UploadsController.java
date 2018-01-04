package com.github.marcinseweryn.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.marcinseweryn.model.Uploads;
import com.github.marcinseweryn.service.UploadsService;

@Controller
public class UploadsController {

	@Autowired
	private UploadsService uploadsService;
	
	@RequestMapping(value = "/uploadProfilePicture", method = RequestMethod.POST)
	public String myAccountUploadProfilePicture(@RequestParam("file") MultipartFile file,Principal principal){
		
		try {
			uploadsService.save("profile_picture_" + principal.getName(), file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/doctor/myAccount";

	}
	
	@RequestMapping(value = "/profile-picture", method = RequestMethod.GET)
	  public void showMyProfilePicture(HttpServletResponse response,HttpServletRequest request, Principal principal) 
	          throws ServletException, IOException{

	    Uploads image = uploadsService.read("profile_picture_" + principal.getName());
	    
	    if(image != null){
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    response.getOutputStream().write(image.getFile());
		    response.getOutputStream().close();
	    }

	}
	
	@RequestMapping(value = "/profile-picture/{ID}", method = RequestMethod.GET)
	  public void showProfilePicture(HttpServletResponse response,HttpServletRequest request,
			  @PathVariable Integer ID) 
	          throws ServletException, IOException{

	    Uploads image = uploadsService.read("profile_picture_" + ID);
	    
	    if(image != null){
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    response.getOutputStream().write(image.getFile());	
		    response.getOutputStream().close();
	    }

	}
	
	@RequestMapping(value = "/uploadNewsPicture", method = RequestMethod.POST)
	public String uploadNewsPicture(@RequestParam("file") MultipartFile file){

		try {
			uploadsService.save("news_picture_" + System.currentTimeMillis(), file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/add-news";
	}
	
	@RequestMapping(value = "/news-picture/{uploadsFileName}", method = RequestMethod.GET)
	  public void showNewsPicture(HttpServletResponse response,HttpServletRequest request,
			  @PathVariable String uploadsFileName) 
	          throws ServletException, IOException{

	    Uploads image = uploadsService.read(uploadsFileName);
	    
	    if(image != null){
		    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		    response.getOutputStream().write(image.getFile());	
		    response.getOutputStream().close();
	    }

	}
}
