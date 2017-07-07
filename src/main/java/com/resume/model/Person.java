package com.resume.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Person implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long personId;
	@NotNull
	@Size(min=2, max=10)
	private String firstName;
	@NotNull
	@Size(min=2, max=10)
	private String lastName;
	@NotNull
	@Size(min=2, max=20)
	private String email;
	/*@NotNull
	private String password;*/
	
	@Transient
	private ArrayList<Education> eduList;
	
	@Transient
	private ArrayList<Experience> expList;
	
	@Transient
	private ArrayList<Skills> skillList;
	
	public Person(){
		expList = new ArrayList<Experience>(); 
		eduList = new ArrayList<Education>(); 
		skillList = new ArrayList<Skills>();
	}
	public Person(String pfname, String plname, String pemail) {	
		this.firstName = pfname; 
		this.lastName = plname; 
		this.email = pemail; 
		expList = new ArrayList <Experience>(); 
		eduList = new ArrayList<Education>(); 
		skillList = new ArrayList<Skills>();
	}

	
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Iterable<Education> getEduList() {
		return eduList;
	}

	public void setEduList(ArrayList<Education> eduList) {
		this.eduList = eduList;
	}

	public ArrayList<Experience> getExpList() {
		return expList;
	}

	public void setExpList(ArrayList<Experience> exp) {
		this.expList =  exp;
	}

	public ArrayList<Skills> getSkillList() {
		return skillList;
	}

	public void setSkillList(ArrayList<Skills> skillList) {
		this.skillList = skillList;
	}

	
	
}
