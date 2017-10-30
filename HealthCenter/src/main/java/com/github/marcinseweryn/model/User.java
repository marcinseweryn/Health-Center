package com.github.marcinseweryn.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import com.github.marcinseweryn.annotation.PeselControlNumber;
import com.github.marcinseweryn.annotation.UniqueEmail;
import com.github.marcinseweryn.annotation.UniquePesel;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	
	private String role, enabled;
	
	@NotNull(message = "{error.gender}")
	private String gender;
	
	@UniquePesel(message = "{error.pesel.unique}")
	@Pattern(regexp = "^[0-9]{11}$", message = "{error.pesel.size}")
	@PeselControlNumber(message = "{error.pesel.controlNumber}")
	private String pesel;
	
	@Pattern(regexp ="(^[a-zA-Z\\s]{2,35}$)", message = "{error.name}")
	private String name, surname;
	
	@Size(min = 5, max = 20, message = "{error.password}")
	private String password;
	
	@Size(min = 2, max = 35, message = "{error.streetAddress.size}")
	@Pattern(regexp = "(^[a-zA-Z0-9\\s\\/]{2,35}$)", message = "{error.streetAddress}")
	private String streetAddress; 
	
	@Pattern(regexp ="(^[a-zA-Z\\s]{2,35}$)", message = "{error.city}")
	private String city;
	
	@Pattern(regexp = "\\d{2}-\\d{3}", message = "{error.postalCode}")
	private String postalCode;
	
	@Pattern(regexp = "^[0-9]{9}$", message = "{error.phone}")
	private String phone;
	
	@UniqueEmail(message = "{error.email.unique}")
	@Size(min = 6, max = 254, message = "{error.email}")
	@Email(message = "{error.email}")
	private String email;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "{error.birthDate}")
	@Past(message = "{error.birthDate}")
	private Date birthDate; 
	
	 
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
		
}
