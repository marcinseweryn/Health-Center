package com.github.marcinseweryn.dao;

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
		Query query = entityManager.createQuery("FROM Duty d WHERE d.ID ="+dutyID);
		List<Duty> list = query.getResultList();
		Duty duty = list.get(0);
		
		return duty;
	}





}
