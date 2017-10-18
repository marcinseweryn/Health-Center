package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.PatientHistory;

public interface PatientHistoryService {
	
	public List<PatientHistory> findPatientHistoryForDoctor(Integer PatientID);

}
