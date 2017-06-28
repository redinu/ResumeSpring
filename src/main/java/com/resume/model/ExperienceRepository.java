package com.resume.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Long>{
	
	public List<Experience>findExperienceByPersonId(Long personId);
}
