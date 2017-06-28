package com.resume.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SkillsRepository extends CrudRepository<Skills, Long> {
 
	public List<Skills> findSkillsByPersonId(Long personId);
}
