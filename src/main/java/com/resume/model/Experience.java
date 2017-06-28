package com.resume.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Experience implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int jobID; 
	private String title; 
	private String company; 
	private LocalDateTime startDate; 
	private LocalDateTime endDate;
	private ArrayList <String> duties;
	private long personId;
	
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
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
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
	
	
}
