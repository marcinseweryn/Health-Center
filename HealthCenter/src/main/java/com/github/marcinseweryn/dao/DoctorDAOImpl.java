package com.github.marcinseweryn.dao;

import java.util.ArrayList;
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
		Query query = entityManager.createQuery("SELECT u.pesel, u.name, u.surname, d.specialization_1, "
				+ "d.specialization_2, d.specialization_3, d.information  "
				+ "FROM User as u, Doctor as d WHERE u.pesel=d.pesel"+ where);
		List<Object[]> objectList = query.getResultList();

		List<Doctor> doctors = new ArrayList<Doctor>();
		
		for(Object[] list : objectList) {
			Doctor doctor = new Doctor();
			doctor.setPesel(list[0].toString());
		    doctor.setName(list[1].toString());
		    doctor.setSurname(list[2].toString());
		    doctor.setSpecialization_1(list[3].toString());
		    doctor.setSpecialization_2(list[4].toString());
		    doctor.setSpecialization_3(list[5].toString());
		    doctor.setInformation(list[6].toString());
		    doctors.add(doctor);
		}
		
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
}
