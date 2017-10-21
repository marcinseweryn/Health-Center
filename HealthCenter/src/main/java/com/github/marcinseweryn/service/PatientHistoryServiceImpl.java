package com.github.marcinseweryn.service;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.PatientHistoryDAOImpl;
import com.github.marcinseweryn.model.PatientHistory;
import com.github.marcinseweryn.pojo.DateFromTo;

@Service
public class PatientHistoryServiceImpl implements PatientHistoryService{

	@Autowired
	private PatientHistoryDAOImpl patientHistoryDAO;
	
	public List<PatientHistory> findPatientHistoryByDateAndPatientID(DateFromTo date, Integer patientID){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
		Date d = null;
		try {
			d = df.parse("2000-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(date.getFrom() == null || date.getTo() ==null){
			date.setFrom(d);
			date.setTo(new Date(System.currentTimeMillis()));			
		}
		
		return patientHistoryDAO.findPatientHistoryByDateAndPatientID(date, patientID);
	}
}
