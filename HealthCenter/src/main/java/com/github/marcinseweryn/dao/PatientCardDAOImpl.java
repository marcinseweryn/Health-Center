package com.github.marcinseweryn.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.PatientCard;

@Repository
public class PatientCardDAOImpl implements PatientCardDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addPatientCard(PatientCard patientCard) {
		
		entityManager.persist(patientCard);
	}

}
