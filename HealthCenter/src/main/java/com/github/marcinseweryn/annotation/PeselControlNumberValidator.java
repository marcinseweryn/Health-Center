package com.github.marcinseweryn.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeselControlNumberValidator implements ConstraintValidator<PeselControlNumber, String>{

	@Override
	public void initialize(PeselControlNumber constraintAnnotation) {
	}

	@Override
	public boolean isValid(String pesel, ConstraintValidatorContext context) {
		
		Integer weight = 1;
		Integer sum = 0;
		for(int i = 0; i < 10; i++){

			sum += weight * Character.getNumericValue(pesel.charAt(i)); 
			
			switch(weight){
				case 1: weight = 3; break;
				case 3: weight = 7; break;
				case 7: weight = 9; break;
				case 9: weight = 1; break;
			}		
		}
		
		Integer controlNumber = 10 - (sum % 10);	
		if(controlNumber == 10) controlNumber = 0;
		
		if(Character.getNumericValue(pesel.charAt(10)) == controlNumber) return true;
		
		return false;
	}

}
