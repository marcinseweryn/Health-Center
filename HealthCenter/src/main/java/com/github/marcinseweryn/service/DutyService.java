package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.pojo.DutyDetailsForUserQueue;

public interface DutyService {

	public void addDuty(List<WorkSchedule> schedules, List<Duty> dutyList);
	
	public List<Duty> findDutyForAdd(Duty duty);
	
	public Duty findDutyByID(Integer dutyID);
	
	public List<Duty> findDutyForDoctorVisitsByDoctorID(String pesel);
	
	public void updateStartDateByID(Integer dutyID);
	
	public void updateEndDateByID(Integer dutyID);
	
	public List<DutyDetailsForUserQueue> findDutyDetailsForCurrentDayByUserID(String pesel);

}
