package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.PatientVisitDetails;

public interface VisitDAO {

	public void addVisit(Visit visit);
	
	public List<Visit> findVisitForDoctorByDutyID(Integer dutyID);
	
	public List<PatientVisitDetails> findVisitDetailsForPatientByPatientID(Integer ID);
	
	public void deleteVisitByID(Integer ID);
	
	public List<Visit> findVisitForQueueByDutyID(Integer dutyID);
	
	public List<Visit> getCurrentQueueByDutyID(Integer dutyID);
	
	public void updatePresence(Integer presenceValue, Integer visitID);
	
}
