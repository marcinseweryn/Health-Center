package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.UserVisitDetails;

public interface VisitService {

	public void addVisit(Integer dutyID, String pesel, Integer positionInQueue);
	
	public List<Visit> findVisitForDoctorByDutyID(Integer dutyID);
	
	public List<UserVisitDetails> findVisitDetailsForUser(String pesel);
	
	public void deleteVisitByID(Integer ID);
	
	public List<Visit> findVisitForQueue(Integer dutyID);
	
}
