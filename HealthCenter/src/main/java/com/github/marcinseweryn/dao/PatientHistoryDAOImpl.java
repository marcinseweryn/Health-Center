package com.github.marcinseweryn.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.github.marcinseweryn.model.PatientHistory;
import com.github.marcinseweryn.pojo.DateFromTo;

@Repository
public class PatientHistoryDAOImpl implements PatientHistoryDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<PatientHistory> findPatientHistoryByDateAndPatientID(DateFromTo date, Integer PatientID){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
		
		Query query = entityManager.createQuery("SELECT  d.date, u.name, u.surname, pc.diagnosis, "
				+ "pc.comments, pc.prescribedMedicines "
					+ "FROM  Visit v, PatientCard pc, Duty d, User u "
						+ "WHERE v.ID = pc.visitID and "
							+ " v.dutyID = d.ID and "
							+ " d.doctorID = u.ID and v.patientID = " + PatientID + " and "
							+ " d.date between '" + df.format(date.getFrom()) + "' and '" + df.format(date.getTo()) + "' "
						+ " ORDER BY d.date DESC");
		
		List<Object[]> objectList = query.getResultList();
		List<PatientHistory> patientHistoryList = new ArrayList<>();
		
		for(Object[] object : objectList){
			PatientHistory patientHistory = new PatientHistory();
			
			patientHistory.setDate((Timestamp) object[0]);
			patientHistory.setDoctor((String) object[1] + " " + object[2]);
			patientHistory.setDiagnosis((String) object[3]);
			patientHistory.setComments((String) object[4]);
			patientHistory.setPrescribedMedicines((String) object[5]);
			
			patientHistoryList.add(patientHistory);
		}
		
		return patientHistoryList;
	}

}
