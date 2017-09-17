package com.github.marcinseweryn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.pojo.WeekWorkSchedule;

@Repository
public class WorkScheduleDAOImpl implements WorkScheduleDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<WorkSchedule> findAll() {
		
		Query query = entityManager.createQuery(" FROM WorkSchedule");
		List<WorkSchedule> list = query.getResultList();
		
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addSchedule(WorkSchedule schedule) {
		entityManager.persist(schedule);
	}

	@Override
	@Transactional (propagation = Propagation.REQUIRED)
	public void deleteSchedules(List<Integer> IDsList) {
		String IDs = IDsList.toString().substring(1, IDsList.toString().length() - 1);
		
		Query query = entityManager.createQuery("DELETE from WorkSchedule ws"
				+ " WHERE ws.ID IN(" + IDs + ")");
		query.executeUpdate();
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateSchedules(List<Integer> IDsList, String columns) {
		String IDs = IDsList.toString().substring(1, IDsList.toString().length() - 1);
		
		Query query = entityManager.createQuery("UPDATE WorkSchedule ws SET " + columns
				+ " WHERE ID IN(" + IDs + ")");
		query.executeUpdate();
	}

	@Override
	public List<WorkSchedule> findSchedules(String columns) {
		
		Query query = entityManager.createQuery("FROM WorkSchedule ws " + columns);	
		List<WorkSchedule> list = query.getResultList();
		
		return list;
	}

	@Override
	public List<WeekWorkSchedule> findWeekWorkScheduleByIDs(List<Integer> IDsList) {
		String IDs = IDsList.toString().substring(1, IDsList.toString().length() - 1);
		
		Query query = entityManager.createQuery("SELECT u.name, u.surname, ws.pesel, "+

			"min(case when ws.day like 'Monday' then ws.start end) as MondayStart, " +
			"min(case when ws.day like 'Monday' then ws.end end) as MondayEnd, " +
			
			"min(case when ws.day like 'Tuesday' then ws.start end) as TuesdayStart, " +
			"min(case when ws.day like 'Tuesday' then ws.end end) as TuesdayEnd, " +
			
			"min(case when ws.day like 'Wednesday' then ws.start end) as WednesdayStart, " +
			"min(case when ws.day like 'Wednesday' then ws.end end) as WednesdayEnd, " +
			
			"min(case when ws.day like 'Thursday' then ws.start end) as ThursdayStart, " +
			"min(case when ws.day like 'Thursday' then ws.end end) as ThursdayEnd, " +
			
			"min(case when ws.day like 'Friday' then ws.start end) as FridayStart, " +
			"min(case when ws.day like 'Friday' then ws.end end) as FridayEnd, " +
			
			"min(case when ws.day like 'Saturday' then ws.start end) as SaturdayStart, " +
			"min(case when ws.day like 'Saturday' then ws.end end) as SaturdayEnd " +
			
			"FROM WorkSchedule as ws, User as u WHERE ws.pesel=u.pesel "
			+ "and ws.pesel IN(" + IDs + ") GROUP BY u.pesel");
		
		List<Object[]> objectList = query.getResultList();
		
		List<WeekWorkSchedule> schedules = new ArrayList<>();
		
		String to = " - ";
		String empty ="--------";
		for(Object[] list : objectList) {
			WeekWorkSchedule schedule = new WeekWorkSchedule();
			schedule.setPesel(list[0].toString());
			schedule.setName(list[1].toString());
			schedule.setSurname(list[2].toString());
			
			if(list[3] != null){
				schedule.setMonday(list[3].toString()+to+list[4].toString());
			}else{
				schedule.setMonday(empty);
			}
			
			if(list[5] != null){
				schedule.setTuesday(list[5].toString()+to+list[6].toString());
			}else{
				schedule.setTuesday(empty);
			}
			
			if(list[7] != null){
				schedule.setWednesday(list[7].toString()+to+list[8].toString());
			}else{
				schedule.setWednesday(empty);
			}
			
			if(list[9] != null){
				schedule.setThursday(list[9].toString()+to+list[10].toString());
			}else{
				schedule.setThursday(empty);
			}
			
			if(list[11] != null){
				schedule.setFriday(list[11].toString()+to+list[12].toString());
			}else{
				schedule.setFriday(empty);
			}
			
			if(list[13] != null){
				schedule.setSaturday(list[13].toString()+to+list[14].toString());
			}else{
				schedule.setSaturday(empty);
			}
			
			schedules.add(schedule);
		}
		return schedules;
	}

}
