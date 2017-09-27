package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.UserVisitDetails;

public interface VisitDAO {

	public void addVisit(Visit visit);
	
	public List<Visit> findVisitForDoctorByDutyID(Integer dutyID);
	
	public List<UserVisitDetails> findVisitDetailsForUser(String pesel);
	
	public void deleteVisitByID(Integer ID);
	
	public List<Visit> findVisitForQueue(Integer dutyID);
	
	public List<Visit> getCurrentQueueByDutyID(Integer dutyID);
	
	public void updatePresence(Integer presenceValue, Integer visitID);
	
}
