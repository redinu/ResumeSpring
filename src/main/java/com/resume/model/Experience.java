package com.resume.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Experience implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int jobID; 
	private String title; 
	private String company;
	
	private String sDate;
	
	private String eDate;
	
	private ArrayList <String> duties;
	private long personId;
	
	public Experience(){	
		
		this.duties = new ArrayList<String>(); 
	}
	
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public ArrayList<String> getDuties() {
		return duties;
	}
	public void setDuties(ArrayList<String> duties) {
		this.duties = duties;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	

	
	
	
}
