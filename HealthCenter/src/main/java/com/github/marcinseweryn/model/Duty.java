package com.github.marcinseweryn.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "duty")
public class Duty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	@Column(name = "free_slots")
	private Integer freeSlots;
	
	private String doctorID, room;
	
	private Timestamp date;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public Integer getFreeSlots() {
		return freeSlots;
	}

	public void setFreeSlots(Integer freeSlots) {
		this.freeSlots = freeSlots;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	
}