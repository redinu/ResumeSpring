package com.resume.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long jobId;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "employer", nullable = false)
	private String employer;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "postedDate")
	private Date postedDate;
	@Column(name = "salary")
	private double salary;
	
	private ArrayList<String> skills;
	
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public ArrayList<String> getSkills() {
		return skills;
	}
	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date date) {
		this.postedDate = date;
	}
	
	
}
