package com.github.marcinseweryn.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class DoctorRatingDAOImpl implements DoctorRatingDAO{

	@PersistenceContext
	private EntityManager entityManager;


}
