package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.PatientHistoryDAOImpl;
import com.github.marcinseweryn.model.PatientHistory;

@Service
public class PatientHistoryServiceImpl implements PatientHistoryService{

	@Autowired
	private PatientHistoryDAOImpl patientHistoryDAO;
	
	public List<PatientHistory> findPatientHistoryForDoctor(Integer patientID){
		
		return patientHistoryDAO.findPatientHistoryForDoctor(patientID);
	}
}
