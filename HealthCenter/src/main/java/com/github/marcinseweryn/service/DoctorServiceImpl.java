package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.DoctorDAO;
import com.github.marcinseweryn.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDAO doctorDAO;
	
	@Override
	public List<Integer> findDoctorsIDsBySpecialization(String specialization) {
		
		return doctorDAO.findDoctorsIDsBySpecialization(specialization);
	}

	@Override
	public List<Doctor> findDoctors(Doctor doctor) {
		String where = "";
		
		if(doctor.getPesel() != null){
			if(!doctor.getPesel().equals("")){
				where += " and u.pesel = '" + doctor.getPesel() + "'"; 
			}
		}
		if(doctor.getName() != null){
			if(!doctor.getName().equals("")){
				where += " and u.name = '" + doctor.getName() + "'";
			}
		}
		if(doctor.getSurname() != null){
			if(!doctor.getSurname().equals("")){
				where += " and u.surname = '" + doctor.getSurname() + "'";
			}
		}
		if(doctor.getSpecialization_1() != null){
			if(!doctor.getSpecialization_1().equals("")){
				where += " and d.specialization_1 = '" + doctor.getSpecialization_1() + "'";
			}
		}
		return doctorDAO.findDoctors(where);
	}

}
