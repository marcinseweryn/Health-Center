package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.WorkSchedule;

public interface WorkScheduleService {
	
	public List<WorkSchedule> findAll();
	
	public void addSchedule(WorkSchedule schedule);
	
	public void deleteSchedules(List<Integer> IDsList);
	
	public void updateSchedules(WorkSchedule schedule, List<Integer> IDsList);
	
	public List<WorkSchedule> findSchedules(WorkSchedule schedule);

}
