package com.resume.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Experience;
import com.resume.model.Person;

public interface ExperienceRepository extends CrudRepository<Experience, Long>{
	
	public List<Experience> findExperienceByPersonId(Long personId);
	public List<Experience> findExperienceByCompany(String company);
}
