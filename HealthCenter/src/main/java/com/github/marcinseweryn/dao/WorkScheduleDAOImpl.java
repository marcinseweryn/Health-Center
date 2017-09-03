package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

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

}
