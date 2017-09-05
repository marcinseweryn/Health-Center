package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.WorkSchedule;

public interface WorkScheduleDAO {

	public List<WorkSchedule> findAll();
	
	public void addSchedule(WorkSchedule schedule);
}
