package com.github.marcinseweryn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.PatientCardDAO;
import com.github.marcinseweryn.model.PatientCard;

@Service
public class PatientCardServiceImpl implements PatientCardService {

	@Autowired
	private PatientCardDAO patientCardDAO;
	
	@Override
	public void addPatientCard(PatientCard patientCard) {
		
		patientCardDAO.addPatientCard(patientCard);
	}

}
