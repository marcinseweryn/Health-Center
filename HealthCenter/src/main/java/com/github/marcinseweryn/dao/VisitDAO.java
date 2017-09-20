package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Visit;

public interface VisitDAO {

	public void addVisit(Visit visit);
	
	public List<Visit> findVisitForDoctorAndDate(Integer dutyID);
}
