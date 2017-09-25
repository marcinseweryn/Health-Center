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
import com.github.marcinseweryn.pojo.UserVisitDetails;

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
	public List<Visit> findVisitForDoctorAndDate(Integer dutyID) {

		Query query = entityManager.createQuery("FROM Visit v WHERE v.dutyID=" + dutyID
				+ " ORDER BY v.positionInQueue ASC");
		
		List<Visit> list = query.getResultList();
		
		return list;
	}

	@Override
	public List<UserVisitDetails> findVisitDetailsForUser(String pesel) {
		
		Query query = entityManager.createQuery("SELECT doc.specialization_1,  doc.specialization_2, "
				+ "doc.specialization_3, d.date, u.name, u.surname, d.room, v.positionInQueue, v.ID "
					+ " FROM Visit v, Duty d, Doctor doc, User u"
						+ " WHERE v.patientPesel ="+ pesel +" and"
						   + " v.dutyID = d.ID and"
						   + " d.doctorID = doc.pesel and"
						   + " doc.pesel = u.pesel");
		
		List<Object[]> objetctList = query.getResultList();
		List<UserVisitDetails> userVisitDetailsList = new ArrayList<>();
		String specialization = null;
		for(Object[] list : objetctList){
			UserVisitDetails userVisitDetails = new UserVisitDetails();
			
			if(list[0] != null) specialization = list[0].toString();
			if(list[1] != null) specialization += ", " + list[1].toString();
			if(list[2] != null) specialization += ", " +list[2].toString();
			
			userVisitDetails.setSpecialization(specialization);
			userVisitDetails.setDate((Timestamp) list[3]);
			userVisitDetails.setDoctorName(list[4].toString());
			userVisitDetails.setDoctorSurname(list[5].toString());
			userVisitDetails.setRoom(list[6].toString());
			userVisitDetails.setPositionInQueue((Integer) list[7]);
			userVisitDetails.setID((Integer) list[8]);
			
			userVisitDetailsList.add(userVisitDetails);
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
	public List<Visit> findVisitForQueue(Integer dutyID) {
		
		Query query = entityManager.createQuery("FROM Visit v WHERE v.dutyID =" + dutyID
				+ " ORDER BY v.presence DESC, v.positionInQueue ASC");
		
		List<Visit> list = query.getResultList();
		
		return list;
	}

}
