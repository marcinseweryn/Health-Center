package com.github.marcinseweryn.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler extends  SavedRequestAwareAuthenticationSuccessHandler {

	   @Override
	   protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

	       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	       String role = authentication.getAuthorities().toString();
	       String targetUrl = "";
	       
	       if(role.contains("ROLE_USER")) {
	           targetUrl = "/user/home";
	       } else if(role.contains("ROLE_ADMIN")) {
	           targetUrl = "/admin/home";
	       }else if(role.contains("ROLE_DOCTOR")) {
	           targetUrl = "/doctor/home";
	       }
	       return targetUrl;
	    }
	
	
}
