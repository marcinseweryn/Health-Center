package com.github.marcinseweryn.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.marcinseweryn.dao.UserDAO;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void initialize(UniqueEmail constraintAnnotation) {	
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		
		if(userDAO.findUserByEmail(email) == null) return true;
		
		return false;
	}

}
