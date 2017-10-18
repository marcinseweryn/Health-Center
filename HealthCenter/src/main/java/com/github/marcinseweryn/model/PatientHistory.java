package com.github.marcinseweryn.model;

import java.sql.Timestamp;

public class PatientHistory {
	
	private Timestamp date;
	private String doctor, comments, diagnosis, recommendations, prescribedMedicines;
	
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}
	public String getPrescribedMedicines() {
		return prescribedMedicines;
	}
	public void setPrescribedMedicines(String prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}
	
	

}
