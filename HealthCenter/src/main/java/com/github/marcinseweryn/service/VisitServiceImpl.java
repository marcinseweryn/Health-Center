package com.github.marcinseweryn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.dao.DutyDAO;
import com.github.marcinseweryn.dao.VisitDAO;
import com.github.marcinseweryn.model.Visit;
import com.github.marcinseweryn.pojo.Presence;
import com.github.marcinseweryn.pojo.UserVisitDetails;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDAO visitDAO;
	
	@Autowired
	private DutyDAO dutyDAO;
	
	@Override
	public void addVisit(Integer dutyID, String pesel, Integer positionInQueue) {
		Visit visit = new Visit();
		
		visit.setDutyID(dutyID);
		visit.setPatientPesel(pesel);
		visit.setPositionInQueue(positionInQueue);
		visit.setPresence(Presence.inQueue.getValue());
		
		dutyDAO.decreaseDutyFreeSlots(dutyID);
		visitDAO.addVisit(visit);
	}

	@Override
	public List<Visit> findVisitForDoctorByDutyID(Integer dutyID) {
			
		return visitDAO.findVisitForDoctorByDutyID(dutyID);
	}

	@Override
	public List<UserVisitDetails> findVisitDetailsForUser(String pesel) {
		
		return visitDAO.findVisitDetailsForUser(pesel);
	}

	@Override
	public void deleteVisitByID(Integer ID) {
		
		visitDAO.deleteVisitByID(ID);
	}

	@Override
	public List<Visit> findVisitForQueue(Integer dutyID) {
		
		return visitDAO.findVisitForQueue(dutyID);
	}

	@Override
	public List<Visit> getCurrentQueueByDutyID(Integer dutyID) {
		
		return visitDAO.getCurrentQueueByDutyID(dutyID);
	}

	@Override
	public void updatePresence(Integer presenceValue, Integer visitID) {
		
		visitDAO.updatePresence(presenceValue, visitID);
	}

}
