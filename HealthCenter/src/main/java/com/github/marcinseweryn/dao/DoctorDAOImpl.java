package com.github.marcinseweryn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.Doctor;

@Repository
public class DoctorDAOImpl implements DoctorDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Integer> findDoctorsIDsBySpecialization(String specialization) {
			
		Query query = entityManager.createQuery("SELECT pesel FROM Doctor "
				+ "WHERE specialization_1 ='" + specialization + "'"
				+ "or specialization_2 = '" + specialization + "' or specialization_3 = '" + specialization + "'");
		List<Integer> list = query.getResultList();
		
		return list;
	}

	@Override
	public List<Doctor> findDoctors(String where) {
		Query query = entityManager.createQuery("FROM Doctor as d "+ where);

		List<Doctor> doctors = query.getResultList();
		
		return doctors;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addDoctor(Doctor doctor) {
		
		entityManager.persist(doctor);	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateDoctorByID(String columns, String pesel) {
		
		Query query = entityManager.createQuery("UPDATE Doctor d SET " + columns
				+ " WHERE d.pesel =" + pesel);
		query.executeUpdate();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteDoctorsByID(List<Integer> usersIDs) {
		String IDs = usersIDs.toString().substring(1, usersIDs.toString().length() - 1);

		Query query = entityManager.createQuery("DELETE FROM Doctor d"
				+ " WHERE d.pesel IN(" + IDs + ")");
		query.executeUpdate();
		
	}
	
}
