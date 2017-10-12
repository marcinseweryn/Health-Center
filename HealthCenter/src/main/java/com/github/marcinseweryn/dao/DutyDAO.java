package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.pojo.DutyDetailsForPatientQueue;

public interface DutyDAO {

	public void addDuty(Duty duty);
	
	public List<Duty> findDutyForAdd(String columns);
	
	public void decreaseDutyFreeSlots(Integer dutyID);
	
	public void increaseDutyFreeSlots(Integer dutyID);
	
	public Duty findDutyByID(Integer dutyID);
	
	public List<Duty> findDutyForDoctorVisitsByDoctorID(Integer ID);
	
	public void updateStartDateByID(Integer dutyID);
	
	public void updateEndDateByID(Integer dutyID);
	
	public List<DutyDetailsForPatientQueue> findDutyDetailsForCurrentDayByPatientID(Integer ID);

}
