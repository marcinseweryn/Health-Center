package com.github.marcinseweryn.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class PatientDAOImpl implements PatientDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePatient(Integer ID, String columns) {
		
		System.out.println(columns);
		System.out.println(ID);
		Query query = entityManager.createQuery("UPDATE Patient SET " + columns +
				" WHERE ID = " + ID);
		
		query.executeUpdate();
		
	}
	

}
