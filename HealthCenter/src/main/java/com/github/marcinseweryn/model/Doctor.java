package com.github.marcinseweryn.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "doctor")
@DiscriminatorValue(value = "Doctor")
public class Doctor extends User {
	
	@Column(name = "medical_title")
	private String medicalTitle;
	
	private String specialization_1, specialization_2, specialization_3, information;


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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	public String getMedicalTitle() {
		return medicalTitle;
	}

	public void setMedicalTitle(String medicalTitle) {
		this.medicalTitle = medicalTitle;
	}

	public Doctor downcastUser(User user) {
		
		Doctor doctor = new Doctor();
		
		doctor.setName(user.getName());
		doctor.setSurname(user.getSurname());
		doctor.setGender(user.getGender());
		doctor.setPesel(user.getPesel());
		doctor.setPassword(user.getPassword());
		doctor.setBirthDate(user.getBirthDate());
		doctor.setCity(user.getCity());
		doctor.setStreetAddress(user.getStreetAddress());
		doctor.setPostalCode(user.getPostalCode());
		doctor.setPhone(user.getPhone());
		doctor.setEmail(user.getEmail());
		doctor.setRole(user.getRole());
		doctor.setEnabled(user.getEnabled());
		
		return doctor;
	}

	

}
