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
		Query query = entityManager.createQuery("FROM Duty d " + columns);	
		List<Duty> list = query.getResultList();
		
		return list;
	}



}
