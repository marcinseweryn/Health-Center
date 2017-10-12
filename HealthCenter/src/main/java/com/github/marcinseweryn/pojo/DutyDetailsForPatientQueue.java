package com.github.marcinseweryn.pojo;

import java.sql.Timestamp;

public class DutyDetailsForPatientQueue {

	private String pesel, name ,surname, specialization_1, specialization_2, specialization_3, room;
	private Timestamp date, startDate, endDate;
	private Integer dutyID;
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSpecialization_1() {
		return specialization_1;
	}
	public void setSpecialization_1(String specialization_1) {
		this.specialization_1 = specialization_1;
	}
	public String getSpecialization_2() {
		return specialization_2;
	}
	public void setSpecialization_2(String specialization_2) {
		this.specialization_2 = specialization_2;
	}
	public String getSpecialization_3() {
		return specialization_3;
	}
	public void setSpecialization_3(String specialization_3) {
		this.specialization_3 = specialization_3;
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
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public Integer getDutyID() {
		return dutyID;
	}
	public void setDutyID(Integer dutyID) {
		this.dutyID = dutyID;
	}
	
	
}
