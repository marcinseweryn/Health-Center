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
		Integer lastDay;
		String room = null, doctorID = null;
		Boolean next = true;
		Boolean max = false;
		
		if(dutyList.size() < 3){
		
			if(dutyList.size()==0){
				lastDay = currentTime.getDay();
			}else{
				for(Duty duty : dutyList){
					if(duty.getDate().getDate() > maxDate.getDate()) maxDate = duty.getDate();
					if(duty.getDate().getMonth() > maxDate.getMonth()) maxDate = duty.getDate();
				}
				lastDay = maxDate.getDay();
			}
			
			for(WorkSchedule schedule: schedules){
				Integer nextDay = lastDay;
				next = true;
				System.out.println(schedule.getDay());
				if(schedule.getDay().equals("Modnay")) day = 1;
				if(schedule.getDay().equals("Tuesday")) day = 2;
				if(schedule.getDay().equals("Wednesday")) day = 3;
				if(schedule.getDay().equals("Thursday")) day = 4;
				if(schedule.getDay().equals("Friday")) day = 5;
				if(schedule.getDay().equals("Saturday")) day = 6;
				
				while(next){
					if(nextDay == 7) nextDay = 0;
					System.out.println(nextDay);
					if(nextDay == day){
						if(nextDay > lastDay){
							if(saveDayMax > nextDay){
								saveDayMax = nextDay;
								next = false;
								max = true;
							}
						}else{
							if(saveDayMin > nextDay){
								saveDayMin = nextDay;
								next = false;
							}
						}
						room = schedule.getRoom();
						doctorID = schedule.getPesel();
						
					}		
					nextDay++;
				}
			}
			next = true;
				
			Calendar cal = Calendar.getInstance();
			   
			if(max == true){
				saveDay = saveDayMax;
			}else{
				saveDay = saveDayMin;
			}
				
			while(next){
			    cal.setTimeInMillis(maxDate.getTime());
			    cal.add(Calendar.DAY_OF_MONTH, 1);
			    maxDate = new Timestamp(cal.getTime().getTime());
			    
			    if(maxDate.getDay() == saveDay) next = false;	  
			}
			
			Duty duty = new Duty();
			
			duty.setDate(maxDate);
			duty.setRoom(room);
			duty.setDoctorID(doctorID);
			dutyDAO.addDuty(duty);
		}
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

}
