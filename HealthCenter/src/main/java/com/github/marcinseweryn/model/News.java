package com.github.marcinseweryn.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	
	private String title, information, author;
	private Timestamp date;
	
	@Column(name = "uploads_ID")
	private Integer uploadsID;
	
	@Column(name = "for_roles")
	private String forRoles;
	

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getUploadsID() {
		return uploadsID;
	}

	public void setUploadsID(Integer uploadsID) {
		this.uploadsID = uploadsID;
	}

	public String getForRoles() {
		return forRoles;
	}

	public void setForRoles(String forRoles) {
		this.forRoles = forRoles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
