package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.Visit;

public interface VisitService {

	public void addVisit(Integer dutyID, String pesel, Integer positionInQueue);
	
	public List<Visit> findVisitForDoctorByDutyID(Integer dutyID);
	
}
