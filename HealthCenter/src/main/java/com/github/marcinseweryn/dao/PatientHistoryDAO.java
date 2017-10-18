package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.PatientHistory;

public interface PatientHistoryDAO {
	
	public List<PatientHistory> findPatientHistoryForDoctor(Integer PatientID);

}
