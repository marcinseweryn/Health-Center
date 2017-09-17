package com.github.marcinseweryn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "visits")
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	@Column(name = "duty_id")
	private Integer dutyId;
	
	@Column(name = "patient_pesel")
	private String patientPesel;
	
	private Integer presence;
	
	@Column(name = "position_in_queue")
	private Integer positionInQueue;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getDutyId() {
		return dutyId;
	}

	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}

	public String getPatientPesel() {
		return patientPesel;
	}

	public void setPatientPesel(String patientPesel) {
		this.patientPesel = patientPesel;
	}

	public Integer getPresence() {
		return presence;
	}

	public void setPresence(Integer presence) {
		this.presence = presence;
	}

	public Integer getPositionInQueue() {
		return positionInQueue;
	}

	public void setPositionInQueue(Integer positionInQueue) {
		this.positionInQueue = positionInQueue;
	}
	
	
	
}
