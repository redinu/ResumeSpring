package com.resume.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Skills implements Serializable {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int skillId;
	@NotNull
	@Size(min=2, max=20)
	private String skill;
	@NotNull
	private int proficiency;
	private long personId;
	
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public int getProficiency() {
		return proficiency;
	}
	public void setProficiency(int proficiency) {
		this.proficiency = proficiency;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	

}
