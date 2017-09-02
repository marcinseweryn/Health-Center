package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.DoctorDAO;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDAO doctorDAO;
	
	@Override
	public List<Integer> findDoctorsIDsBySpecialization(String specialization) {
		
		return doctorDAO.findDoctorsIDsBySpecialization(specialization);
	}

}
