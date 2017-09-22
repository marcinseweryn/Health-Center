package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Duty;

public interface DutyDAO {

	public void addDuty(Duty duty);
	
	public List<Duty> findDutyForAdd(String columns);
	
	public void decreaseDutyFreeSlots(Integer dutyID);
	
	public Duty findDutyByID(Integer dutyID);

}
