package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class DoctorDAOImpl implements DoctorDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Integer> findDoctorsIDsBySpecialization(String specialization) {
			
		Query query = entityManager.createQuery("SELECT pesel FROM Doctor "
				+ "WHERE specialization_1 ='" + specialization + "'"
				+ "or specialization_2 = '" + specialization + "' or specialization_3 = '" + specialization + "'");
		List<Integer> list = query.getResultList();
		
		return list;
	}

}
