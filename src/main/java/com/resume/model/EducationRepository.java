package com.resume.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EducationRepository  extends CrudRepository<Education,Long>{
	
	public List<Education> findEducationByPersonId(Long personId);

}
