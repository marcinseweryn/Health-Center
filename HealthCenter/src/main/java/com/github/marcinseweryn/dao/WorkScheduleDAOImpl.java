package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.User;
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

}
