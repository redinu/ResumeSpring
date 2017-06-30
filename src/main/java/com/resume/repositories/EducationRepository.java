package com.resume.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Education;

public interface EducationRepository  extends CrudRepository<Education,Long>{
	
	public List<Education> findEducationByPersonId(Long personId);

}
