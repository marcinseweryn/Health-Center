package com.github.marcinseweryn.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.marcinseweryn.dao.UserDAO;

public class UniquePeselValidator implements ConstraintValidator<UniquePesel, String> {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void initialize(UniquePesel constraintAnnotation) {
	}

	@Override
	public boolean isValid(String pesel, ConstraintValidatorContext context) {
		
		if(userDAO.findUserByPesel(pesel) == null) return true;
		
		return false;
	}

}
