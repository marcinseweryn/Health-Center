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
	
	@Column(name = "Uploads_file_name")
	private String uploadsFileName;
	
	@Column(name = "for_roles")
	private String forRoles;
	
	@Column(name = "update_author")
	private String updateAuthor;
	
	@Column(name = "update_date")
	private Timestamp updateDate;
	

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(String uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
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

	public String getUpdateAuthor() {
		return updateAuthor;
	}

	public void setUpdateAuthor(String updateAuthor) {
		this.updateAuthor = updateAuthor;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
}
