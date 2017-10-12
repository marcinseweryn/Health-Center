package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Doctor;

public interface DoctorDAO {
	
	public void addDoctor(Doctor doctor);
	
	public void updateDoctorByID(String columns, Integer ID);
	
	public List<Doctor> findDoctors(String where);
	
	public Doctor findDoctorByID(Integer ID);
	
	public List<Integer>  findDoctorsIDsBySpecialization(String specialization);

}
