package com.github.marcinseweryn.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.DoctorRatingDAO;
import com.github.marcinseweryn.dao.UserDAO;
import com.github.marcinseweryn.model.DoctorRating;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.pojo.DoctorProfileComments;

@Service
public class DoctorRatingServiceImpl implements DoctorRatingService {

	@Autowired
	private DoctorRatingDAO doctorRatingDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void saveRating(DoctorRating rating) {
		rating.setCommentDate(new Timestamp(System.currentTimeMillis()));
		doctorRatingDAO.saveRating(rating);
	}

	@Override
	public Double getDoctorRatingByDoctorID(Integer doctorID) {
		
		Double rating, sum = 0.0;
		List<DoctorRating> list = doctorRatingDAO.findDoctorRatingByDoctorID(doctorID);
		
		for(DoctorRating dr : list){
			sum += dr.getRating();
		}
		
		rating = sum / list.size();
		rating = rating*100;
		rating = (double) Math.round(rating);
		rating = rating /100;
		
		return rating;
	}

	@Override
	public List<DoctorProfileComments> getDoctorProfileCommentsByDoctorID(Integer doctorID) {
		
		List<DoctorRating> doctorRatingList = doctorRatingDAO.findDoctorRatingByDoctorID(doctorID);
		List<DoctorProfileComments> doctorProfileCommentsList = new ArrayList<>();	
		
		for(DoctorRating dr : doctorRatingList){
			DoctorProfileComments doctorProfileComments = new DoctorProfileComments();
			User user = userDAO.findUserByID(dr.getUserID());
			
			doctorProfileComments.setUser(user.getName() + " " + user.getSurname());
			doctorProfileComments.setComment(dr.getComment());
			doctorProfileComments.setRating(dr.getRating());
			doctorProfileComments.setCommentDate(dr.getCommentDate());
			
			doctorProfileCommentsList.add(doctorProfileComments);
		}
		
		return doctorProfileCommentsList;
	}
	
	


}
