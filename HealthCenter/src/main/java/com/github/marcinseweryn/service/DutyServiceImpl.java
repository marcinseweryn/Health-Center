package com.github.marcinseweryn.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.DutyDAO;
import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.WorkSchedule;

@Service
public class DutyServiceImpl implements DutyService {

	@Autowired
	private DutyDAO dutyDAO;
	
	@Override
	public void addDuty(List<WorkSchedule> schedules, List<Duty> dutyList) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		Timestamp maxDate = new Timestamp(System.currentTimeMillis());
		Integer day = 1, saveDayMax = 10, saveDayMin = 10, saveDay;
		Integer startMax = null, startMin = null, start, endMax = null, endMin = null, end;
		Integer lastDay, numberOfPatients;
		String room = null, roomMax = null, roomMin = null, doctorID = null;
		Boolean next = true;	//loop state
		Boolean max = false;	//to determine if next duty day was bigger than last
		
		
		if(dutyList.size()==0){	
			lastDay = currentTime.getDay();
		}else{
			for(Duty duty : dutyList){		// get the most recent date
				if(duty.getDate().getDate() > maxDate.getDate()) maxDate = duty.getDate();
				if(duty.getDate().getMonth() > maxDate.getMonth()) maxDate = duty.getDate();
			}
			lastDay = maxDate.getDay();
		}
		
		for(WorkSchedule schedule: schedules){
			Integer nextDay = lastDay;
			next = true;

			if(schedule.getDay().equals("Monday")) day = 1;
			if(schedule.getDay().equals("Tuesday")) day = 2;
			if(schedule.getDay().equals("Wednesday")) day = 3;
			if(schedule.getDay().equals("Thursday")) day = 4;
			if(schedule.getDay().equals("Friday")) day = 5;
			if(schedule.getDay().equals("Saturday")) day = 6;
			
			while(next){
				if(nextDay == 7) nextDay = 0;

				if(nextDay == day){				
					if(nextDay > lastDay){   		//if next duty day is bigger than last
						if(saveDayMax > nextDay){	//if next day is smaller
							saveDayMax = nextDay;
							roomMax = schedule.getRoom();
							startMax = schedule.getStart();
							endMax = schedule.getEnd();
							max = true;	
							}
					}else{						   //if next duty day is smaller than last	
						if(saveDayMin > nextDay){	//if next day is smaller
							saveDayMin = nextDay;
							roomMin = schedule.getRoom();
							startMin = schedule.getStart();
							endMin = schedule.getEnd();
						}	
					}
					doctorID = schedule.getPesel();
					next = false;
				}		
				nextDay++;
			}
		}
		next = true;
			
		Calendar cal = Calendar.getInstance();
		   
		if(max == true){
			saveDay = saveDayMax;		// for bigger days, example (last day friday - 5 next day saturday - 6)
			room = roomMax;
			start = startMax;
			end = endMax;
		}else{
			saveDay = saveDayMin;		// for smaller days
			room = roomMin;
			start = startMin;
			end = endMin;
		}
			
		while(next){
		    cal.setTimeInMillis(maxDate.getTime());
		    cal.add(Calendar.DAY_OF_MONTH, 1);
		    maxDate = new Timestamp(cal.getTime().getTime());
		    
		    if(maxDate.getDay() == saveDay) next = false;	// if the next date is equal to the day of the next duty
		}
		
		Duty duty = new Duty();
		
		maxDate.setHours(start);
		maxDate.setMinutes(0);
		maxDate.setSeconds(0);
		
		numberOfPatients = (end - start) * 6;  // 6 patients per hour
		
		duty.setDate(maxDate);
		duty.setRoom(room);
		duty.setDoctorID(doctorID);
		duty.setFreeSlots(numberOfPatients);
		dutyDAO.addDuty(duty);
	}

	
	
	@Override
	public List<Duty> findDutyForAdd(Duty duty) {
		String columns = "";
		
		if(duty.getDoctorID() != null){
			if(!duty.getDoctorID().equals("")){
				columns += "d.doctorID = '" + duty.getDoctorID() + "' and "; 
			}
		}
		if(duty.getRoom() != null){
			if(!duty.getRoom().equals("")){
				columns += "d.room = '" + duty.getRoom() + "' and ";
			}
		}
		if(duty.getDate() != null){
			columns += "d.date >= '" + duty.getDate() + "' and ";
		}
		if(duty.getID() != null){
				columns += "ws.end = '" + duty.getID() + "' and ";
		}
		
		if(columns.length() > 0){
			columns = columns.substring(0, columns.length() - 4);
		}
		
		if(!columns.equals("")){
			String where = "WHERE ";
			columns = where+columns;
			return dutyDAO.findDutyForAdd(columns);
		}else{
			return dutyDAO.findDutyForAdd(columns);
		}
	}



	@Override
	public Duty findDutyByID(Integer dutyID) {
		
		return dutyDAO.findDutyByID(dutyID);
	}

	
	@Override
	public List<Duty> findDutyForDoctorVisitsByDoctorID(String pesel) {
	
		return dutyDAO.findDutyForDoctorVisitsByDoctorID(pesel);
	}



	@Override
	public void updateStartDateByID(Integer dutyID) {
		
		dutyDAO.updateStartDateByID(dutyID);
	}



	@Override
	public void updateEndDateByID(Integer dutyID) {
		
		dutyDAO.updateEndDateByID(dutyID);
	}
}
