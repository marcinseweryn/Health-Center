package com.github.marcinseweryn.pojo;

import java.sql.Timestamp;

public class PatientVisitDetails {
	
	private String specialization, doctorName, doctorSurname, room;
	private Integer positionInQueue, ID, dutyID;
	private Timestamp date;
	
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getDutyID() {
		return dutyID;
	}
	public void setDutyID(Integer dutyID) {
		this.dutyID = dutyID;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorSurname() {
		return doctorSurname;
	}
	public void setDoctorSurname(String doctorSurname) {
		this.doctorSurname = doctorSurname;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Integer getPositionInQueue() {
		return positionInQueue;
	}
	public void setPositionInQueue(Integer positionInQueue) {
		this.positionInQueue = positionInQueue;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}

}
