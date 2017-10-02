package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.Doctor;

public interface DoctorDAO {
	
	public void addDoctor(Doctor doctor);
	
	public void updateDoctorByID(String columns, String pesel);
	
	public List<Doctor> findDoctors(String where);
	
	public List<Integer>  findDoctorsIDsBySpecialization(String specialization);
	
	public void deleteDoctorsByID(List<Integer> doctorIDs);

}
