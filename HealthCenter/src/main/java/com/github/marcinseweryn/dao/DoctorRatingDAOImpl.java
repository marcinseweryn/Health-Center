package com.github.marcinseweryn.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.DoctorRating;

@Repository
public class DoctorRatingDAOImpl implements DoctorRatingDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveRating(DoctorRating rating) {
		
		entityManager.persist(rating);
	}

	@Override
	public List<DoctorRating> findDoctorRatingByDoctorID(Integer doctorID) {
		
		Query query = entityManager.createQuery("FROM DoctorRating WHERE doctorID = " + doctorID);
		
		return query.getResultList();
	}


}
