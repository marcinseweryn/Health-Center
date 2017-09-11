package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Doctor;

public interface DoctorDAO {
	
	public List<Doctor> findDoctors(String where);
	
	public List<Integer>  findDoctorsIDsBySpecialization(String specialization);

}
