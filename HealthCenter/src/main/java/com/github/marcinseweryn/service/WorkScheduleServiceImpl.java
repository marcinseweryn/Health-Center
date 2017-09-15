package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.WorkScheduleDAO;
import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.pojo.WeekWorkSchedule;

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

	@Override
	public void deleteSchedules(List<Integer> IDsList) {
		if(IDsList.size() != 0){
		workScheduleDAO.deleteSchedules(IDsList);
		}
	}

	public void updateSchedules(WorkSchedule schedule, List<Integer> IDsList) {
		String columns = "";
		
		if(schedule.getDay() != null){
			if(!schedule.getDay().equals("")){
				columns += "ws.day = '" + schedule.getDay() + "' ,"; 
			}
		}
		if(schedule.getRoom() != null){
			if(!schedule.getRoom().equals("")){
				columns += "ws.room = '" + schedule.getRoom() + "' ,";
			}
		}
		if(schedule.getStart() != null){
			if(!schedule.getStart().equals("")){
				columns += "ws.start = '" + schedule.getStart() + "' ,";
			}
		}
		if(schedule.getEnd() != null){
			if(!schedule.getEnd().equals("")){
				columns += "ws.end = '" + schedule.getEnd() + "' ,";
			}
		}
		if(schedule.getPesel() != null){
			if(!schedule.getPesel().equals("")){
				columns += "ws.pesel = '" + schedule.getPesel() + "' ,";
			}
		}
		
		if(columns.length() > 0){
			columns = columns.substring(0, columns.length() - 1);
		}
		
		if(!columns.equals("")){
			workScheduleDAO.updateSchedules(IDsList, columns);;
		}
		
	}

	@Override
	public List<WorkSchedule> findSchedules(WorkSchedule schedule) {
		String columns = "";
		
		if(schedule.getDay() != null){
			if(!schedule.getDay().equals("")){
				columns += "ws.day = '" + schedule.getDay() + "' and "; 
			}
		}
		if(schedule.getRoom() != null){
			if(!schedule.getRoom().equals("")){
				columns += "ws.room = '" + schedule.getRoom() + "' and ";
			}
		}
		if(schedule.getStart() != null){
			if(!schedule.getStart().equals("")){
				columns += "ws.start = '" + schedule.getStart() + "' and ";
			}
		}
		if(schedule.getEnd() != null){
			if(!schedule.getEnd().equals("")){
				columns += "ws.end = '" + schedule.getEnd() + "' and ";
			}
		}
		if(schedule.getPesel() != null){
			if(!schedule.getPesel().equals("")){
				columns += "ws.pesel = '" + schedule.getPesel() + "' and ";
			}
		}
		
		if(columns.length() > 0){
			columns = columns.substring(0, columns.length() - 4);
		}
		
		if(!columns.equals("")){
			String where = "WHERE ";
			columns = where+columns;
			return workScheduleDAO.findSchedules(columns);
		}else{
			return workScheduleDAO.findSchedules(columns);
		}
	}

	@Override
	public List<WeekWorkSchedule> findWeekWorkScheduleByIDs(List<Integer> IDsList) {
		return workScheduleDAO.findWeekWorkScheduleByIDs(IDsList);
	}

}
