package com.github.marcinseweryn.model;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor {
	
	@Id
	private String pesel;
	
	private String specialization_1, specialization_2, specialization_3, information;

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	

}