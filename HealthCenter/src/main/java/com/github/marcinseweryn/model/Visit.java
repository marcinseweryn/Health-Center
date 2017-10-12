package com.github.marcinseweryn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "visit")
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	@Column(name = "duty_ID")
	private Integer dutyID;
	
	@Column(name = "patient_ID")
	private Integer patientID;
	
	private Integer presence;
	
	@Column(name = "position_in_queue")
	private Integer positionInQueue;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public Integer getDutyID() {
		return dutyID;
	}

	public void setDutyID(Integer dutyID) {
		this.dutyID = dutyID;
	}

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
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
