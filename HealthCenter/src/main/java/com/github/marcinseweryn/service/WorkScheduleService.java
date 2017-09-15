package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.pojo.WeekWorkSchedule;

public interface WorkScheduleService {
	
	public List<WorkSchedule> findAll();
	
	public void addSchedule(WorkSchedule schedule);
	
	public void deleteSchedules(List<Integer> IDsList);
	
	public void updateSchedules(WorkSchedule schedule, List<Integer> IDsList);
	
	public List<WorkSchedule> findSchedules(WorkSchedule schedule);
	
	public List<WeekWorkSchedule> findWeekWorkScheduleByIDs(List<Integer> IDsList);

}
