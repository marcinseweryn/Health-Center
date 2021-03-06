package com.github.marcinseweryn.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.WorkSchedule;
import com.github.marcinseweryn.pojo.DutyDetailsForPatientQueue;
import com.github.marcinseweryn.pojo.Presence;

@Repository
public class DutyDAOImpl implements DutyDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addDuty(Duty duty) {
		entityManager.persist(duty);		
	}

	@Override
	public List<Duty> findDutyForAdd(String columns) {
		Query query = entityManager.createQuery("FROM Duty d " + columns + " ORDER BY d.date ASC");	
		List<Duty> list = query.getResultList();
		
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void decreaseDutyFreeSlots(Integer dutyID) {
		Query query = entityManager.createQuery("UPDATE Duty d SET d.freeSlots=d.freeSlots - 1"
				+ " WHERE d.ID =" +dutyID);
		query.executeUpdate();
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void increaseDutyFreeSlots(Integer dutyID) {
		Query query = entityManager.createQuery("UPDATE Duty d SET d.freeSlots=d.freeSlots + 1"
				+ " WHERE d.ID =" +dutyID);
		query.executeUpdate();	
	}

	@Override
	public Duty findDutyByID(Integer dutyID) {
		
		Duty duty = entityManager.find(Duty.class, dutyID);
		
		return duty;
	}

	@Override
	public List<Duty> findDutyForDoctorVisitsByDoctorID(Integer ID) {
		
		Query query = entityManager.createQuery("FROM Duty d WHERE d.endDate IS NULL and"
				+ " d.date >= CURRENT_DATE and d.doctorID =" + ID + " ORDER BY d.date ASC");
		List<Duty> list  = query.getResultList();
		
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateStartDateByID(Integer dutyID) {
		
		Query query = entityManager.createQuery("UPDATE Duty d SET d.startDate = CURRENT_TIMESTAMP"
				+ " WHERE d.ID =" + dutyID);
		query.executeUpdate();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEndDateByID(Integer dutyID) {
	
		Query query = entityManager.createQuery("UPDATE Duty d SET d.endDate = CURRENT_TIMESTAMP" 
				+ " WHERE d.ID =" + dutyID);
		query.executeUpdate();
	}

	@Override
	public List<DutyDetailsForPatientQueue> findDutyDetailsForCurrentDayByPatientID(Integer ID){
		
		Query query = entityManager.createQuery("SELECT d.ID, d.date, d.room, d.startDate, d.endDate,"
			+ " u.name, u.surname, doc.specialization_1, doc.specialization_2, doc.specialization_3"
				+ " FROM Duty d, Visit v, Doctor doc, User u "
						+ "WHERE d.ID = v.dutyID and "
						+ " doc.ID = d.doctorID and "
							+ " date(d.date) = current_date() and v.patientID =" + ID
							+ " and d.endDate IS NULL and"
							+ " v.presence IN("+Presence.inQueue.getValue()+","+Presence.firstAbsent.getValue()+")" 
							+ " GROUP BY d.ID "
							+ " ORDER BY d.date ASC");
		
		List<Object[]> objectList = query.getResultList();
		List<DutyDetailsForPatientQueue> list = new ArrayList<>();
		
		for(Object[] object : objectList){
			DutyDetailsForPatientQueue detailsForPatientQueue = new DutyDetailsForPatientQueue();
			
			detailsForPatientQueue.setDutyID((Integer)object[0]);
			detailsForPatientQueue.setDate((Timestamp) object[1]);
			detailsForPatientQueue.setRoom((String) object[2]);
			detailsForPatientQueue.setStartDate((Timestamp) object[3]);
			detailsForPatientQueue.setEndDate((Timestamp) object[4]);
			detailsForPatientQueue.setName((String) object[5]);
			detailsForPatientQueue.setSurname((String) object[6]);
			detailsForPatientQueue.setSpecialization_1((String) object[7]);
			detailsForPatientQueue.setSpecialization_2((String) object[8]);
			detailsForPatientQueue.setSpecialization_3((String) object[9]);
			
			list.add(detailsForPatientQueue);
		}
		
		return list;
	}

}
