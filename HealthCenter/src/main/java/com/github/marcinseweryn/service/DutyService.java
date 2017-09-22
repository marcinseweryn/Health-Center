package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.WorkSchedule;

public interface DutyService {

	public void addDuty(List<WorkSchedule> schedules, List<Duty> dutyList);
	
	public List<Duty> findDutyForAdd(Duty duty);
	
	public Duty findDutyByID(Integer dutyID);

}
