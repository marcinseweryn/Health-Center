package com.github.marcinseweryn.service;

import java.util.List;

public interface DoctorService {

	public List<Integer> findDoctorsIDsBySpecialization(String specialization);
}
