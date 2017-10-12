package com.github.marcinseweryn.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
@DiscriminatorValue(value = "Patient")
public class Patient extends User {
	
	private String sensitizations;
	
	@Column(name = "chronic_diseases")
	private String chronicDiseases;
	
	@Column(name = "solid_drugs")
	private String solidDrugs;

	
	public String getSensitizations() {
		return sensitizations;
	}

	public void setSensitizations(String sensitizations) {
		this.sensitizations = sensitizations;
	}

	public String getChronicDiseases() {
		return chronicDiseases;
	}

	public void setChronicDiseases(String chronicDiseases) {
		this.chronicDiseases = chronicDiseases;
	}

	public String getSolidDrugs() {
		return solidDrugs;
	}

	public void setSolidDrugs(String solidDrugs) {
		this.solidDrugs = solidDrugs;
	}
	
	public Patient downcastUser(User user) {
		
		Patient patient = new Patient();
		
		patient.setName(user.getName());
		patient.setSurname(user.getSurname());
		patient.setGender(user.getGender());
		patient.setPesel(user.getPesel());
		patient.setPassword(user.getPassword());
		patient.setBirthDate(user.getBirthDate());
		patient.setCity(user.getCity());
		patient.setStreetAddress(user.getStreetAddress());
		patient.setPostalCode(user.getPostalCode());
		patient.setPhone(user.getPhone());
		patient.setEmail(user.getEmail());
		patient.setRole(user.getRole());
		patient.setEnabled(user.getEnabled());
		
		return patient;
	}
	
}
