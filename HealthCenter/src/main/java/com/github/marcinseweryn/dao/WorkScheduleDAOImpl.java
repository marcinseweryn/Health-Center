package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.WorkSchedule;

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

}
