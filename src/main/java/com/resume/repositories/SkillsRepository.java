package com.resume.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Job;
import com.resume.model.Skills;

public interface SkillsRepository extends CrudRepository<Skills, Long> {
 
	public List<Skills> findSkillsByPersonId(Long personId);

	public List<Skills> findBySkill(String skill);

	public List<Skills> findByJob_JobId(Long jobId);
	
	}
