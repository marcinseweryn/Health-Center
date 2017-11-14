package com.github.marcinseweryn.dao;

import java.util.List;

import com.github.marcinseweryn.model.DoctorRating;

public interface DoctorRatingDAO {
	
	public void saveRating(DoctorRating rating);
	
	public List<DoctorRating> findDoctorRatingByDoctorID(Integer doctorID);
	
}
