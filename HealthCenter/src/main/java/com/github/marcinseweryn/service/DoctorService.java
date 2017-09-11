package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.Doctor;

public interface DoctorService {
	
	public List<Doctor> findDoctors(Doctor doctor);

	public List<Integer> findDoctorsIDsBySpecialization(String specialization);
}
