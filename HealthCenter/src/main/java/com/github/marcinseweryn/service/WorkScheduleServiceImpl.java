package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.WorkScheduleDAO;
import com.github.marcinseweryn.model.WorkSchedule;

@Service
public class WorkScheduleServiceImpl implements WorkScheduleService{

	@Autowired
	private WorkScheduleDAO workScheduleDAO;
	
	@Override
	public List<WorkSchedule> findAll() {
		return workScheduleDAO.findAll();
	}

	@Override
	public void addSchedule(WorkSchedule schedule) {
		workScheduleDAO.addSchedule(schedule);
		
	}

}
