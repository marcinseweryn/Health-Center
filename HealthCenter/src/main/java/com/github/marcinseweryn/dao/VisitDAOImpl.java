package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.Visit;

@Repository
public class VisitDAOImpl implements VisitDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addVisit(Visit visit) {
		entityManager.persist(visit);
	}

	@Override
	public List<Visit> findVisitForDoctorAndDate(Integer dutyID) {

		Query query = entityManager.createQuery("FROM Visit v WHERE v.dutyID=" + dutyID
				+ " ORDER BY v.positionInQueue ASC");
		
		List<Visit> list = query.getResultList();
		
		return list;
	}

}
