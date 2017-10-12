package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.Doctor;

public interface DoctorService {
	
	public void addDoctor(Doctor doctor);
	
	public void updateDoctorByID(Doctor doctor, Integer ID);
	
	public List<Doctor> findDoctors(Doctor doctor);
	
	public Doctor findDoctorByID(Integer ID);

	public List<Integer> findDoctorsIDsBySpecialization(String specialization);

}
