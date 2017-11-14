package com.github.marcinseweryn.service;

import java.util.List;

import com.github.marcinseweryn.model.DoctorRating;
import com.github.marcinseweryn.pojo.DoctorProfileComments;

public interface DoctorRatingService {
	
	public void saveRating(DoctorRating rating);
	
	public Double getDoctorRatingByDoctorID(Integer doctorID);
	
	public List<DoctorProfileComments> getDoctorProfileCommentsByDoctorID(Integer doctorID);
	
}
