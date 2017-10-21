package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.PatientHistory;
import com.github.marcinseweryn.pojo.DateFromTo;

public interface PatientHistoryDAO {
	
	public List<PatientHistory> findPatientHistoryByDateAndPatientID(DateFromTo date, Integer PatientID);

}
