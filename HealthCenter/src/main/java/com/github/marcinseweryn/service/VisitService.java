package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.PatientVisitDetails;

public interface VisitService {

	public void addVisit(Integer dutyID, Integer patientID, Integer positionInQueue);
	
	public Visit findVisitByID(Integer ID);
	
	public List<Visit> findVisitForDoctorByDutyID(Integer dutyID);
	
	public List<PatientVisitDetails> findVisitDetailsForPatientByPatientID(Integer ID);
	
	public void deleteVisitByID(Integer visitID, Integer dutyID);
	
	public List<Visit> findVisitForQueueByDutyID(Integer dutyID);
	
	public List<Visit> getCurrentQueueByDutyID(Integer dutyID);
	
	public void updatePresence(Integer presenceValue, Integer visitID);
	
}
