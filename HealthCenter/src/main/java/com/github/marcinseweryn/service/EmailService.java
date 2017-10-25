package com.github.marcinseweryn.service;

import com.github.marcinseweryn.model.User;

public interface EmailService {
	
	public String forgottenLoginDataFirstStep(String email);
	
	public void forgottenLoginDataSecondStep(String email, User user);

}
