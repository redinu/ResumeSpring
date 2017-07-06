package com.resume.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Education implements Serializable{
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int eduId;
		@NotNull
		@Size(min=2, max=30)
		private String typeOfDegree;
		@NotNull
		@Size(min=2, max=30)
		private String institute; 
		
		private int endYear;
		private long personId;
		
		public int getEduId() {
			return eduId;
		}
		public void setEduId(int eduId) {
			this.eduId = eduId;
		}
		public String getTypeOfDegree() {
			return typeOfDegree;
		}
		public void setTypeOfDegree(String typeOfDegree) {
			this.typeOfDegree = typeOfDegree;
		}
		public String getInstitute() {
			return institute;
		}
		public void setInstitute(String institute) {
			this.institute = institute;
		}
		
		public long getPersonId() {
			return personId;
		}
		public void setPersonId(long personId) {
			this.personId = personId;
		}
		public int getEndYear() {
			return endYear;
		}
		public void setEndYear(int endYear) {
			this.endYear = endYear;
		}
	
		
		
		
		

}


