package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.PatientHistory;
import com.github.marcinseweryn.pojo.DateFromTo;

public interface PatientHistoryService {
	
	public List<PatientHistory> findPatientHistoryByDateAndPatientID(DateFromTo date, Integer PatientID);

}
