package com.github.marcinseweryn.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.PatientVisitDetails;

@Repository
public class VisitDAOImpl implements VisitDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addVisit(Visit visit) {
		entityManager.persist(visit);
	}

	@Override
	public List<Visit> findVisitForDoctorByDutyID(Integer dutyID) {

		Query query = entityManager.createQuery("FROM Visit v WHERE v.dutyID=" + dutyID
				+ " ORDER BY v.positionInQueue ASC");
		
		List<Visit> list = query.getResultList();
		
		return list;
	}

	@Override
	public List<PatientVisitDetails> findVisitDetailsForPatientByPatientID(Integer ID) {
		
		Query query = entityManager.createQuery("SELECT doc.specialization_1,  doc.specialization_2, "
				+ "doc.specialization_3, d.date, u.name, u.surname, d.room, v.positionInQueue, v.ID, d.ID "
					+ " FROM Visit v, Duty d, Doctor doc, User u"
						+ " WHERE v.patientID ="+ ID +" and"
						   + " v.dutyID = d.ID and"
						   + " d.doctorID = doc.ID and"
						   + " doc.ID = u.ID"
						   + " ORDER BY d.date ASC");
		
		List<Object[]> objetctList = query.getResultList();
		List<PatientVisitDetails> userVisitDetailsList = new ArrayList<>();
		String specialization = null;
		for(Object[] list : objetctList){
			PatientVisitDetails patientVisitDetails = new PatientVisitDetails();
			
			if(list[0] != null) specialization = list[0].toString();
			if(list[1] != null) specialization += ", " + list[1].toString();
			if(list[2] != null) specialization += ", " +list[2].toString();
			
			patientVisitDetails.setSpecialization(specialization);
			patientVisitDetails.setDate((Timestamp) list[3]);
			patientVisitDetails.setDoctorName(list[4].toString());
			patientVisitDetails.setDoctorSurname(list[5].toString());
			patientVisitDetails.setRoom(list[6].toString());
			patientVisitDetails.setPositionInQueue((Integer) list[7]);
			patientVisitDetails.setID((Integer) list[8]);
			patientVisitDetails.setDutyID((Integer) list[9]);
			
			userVisitDetailsList.add(patientVisitDetails);
		}
		
		return userVisitDetailsList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteVisitByID(Integer ID) {
		
		Query query = entityManager.createQuery("DELETE FROM Visit v WHERE v.ID = " + ID);
		query.executeUpdate();		
	}

	@Override
	public List<Visit> findVisitForQueueByDutyID(Integer dutyID) {
		
		Query query = entityManager.createQuery("FROM Visit v WHERE v.dutyID =" + dutyID
				+ " ORDER BY v.presence ASC, v.positionInQueue ASC");
		
		List<Visit> list = query.getResultList();
		
		return list;
	}

	@Override
	public List<Visit> getCurrentQueueByDutyID(Integer dutyID) {

		Query query = entityManager.createQuery("FROM Visit "
				+ "WHERE dutyID = " + dutyID + " and presence IN(3,4) "
				+ "ORDER BY presence ASC, positionInQueue ASC");
		
		List<Visit> list = query.getResultList();
		
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePresence(Integer presenceValue, Integer visitID) {

		Query query = entityManager.createQuery("UPDATE Visit v SET presence =" + presenceValue
				+ " WHERE v.ID =" + visitID);
		query.executeUpdate();
		
	}

}
