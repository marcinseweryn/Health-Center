package com.github.marcinseweryn.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.Duty;
import com.github.marcinseweryn.model.WorkSchedule;

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
	public Duty findDutyByID(Integer dutyID) {
		
		Duty duty = entityManager.find(Duty.class, dutyID);
		
		return duty;
	}

	@Override
	public List<Duty> findDutyForDoctorVisitsByDoctorID(String pesel) {
		
		Query query = entityManager.createQuery("FROM Duty d WHERE d.endDate IS NULL and"
				+ " d.date >= CURRENT_DATE and d.doctorID =" + pesel + " ORDER BY d.date ASC");
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

}
