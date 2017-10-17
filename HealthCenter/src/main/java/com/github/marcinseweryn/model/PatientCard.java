package com.github.marcinseweryn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient_card")
public class PatientCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	@Column(name = "patient_ID")
	private Integer patientID;
	
	@Column(name = "visit_ID")
	private Integer visitID;
	
	@Column(name = "prescribed_medicines")
	private String prescribedMedicines;
	
	private String comments, diagnosis, recommendations;

	
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}

	public Integer getVisitID() {
		return visitID;
	}

	public void setVisitID(Integer visitID) {
		this.visitID = visitID;
	}

	public String getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(String prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
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
	

}
