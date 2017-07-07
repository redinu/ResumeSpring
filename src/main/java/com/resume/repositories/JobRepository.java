package com.resume.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.resume.model.Job;
import com.resume.model.Skills;

public interface JobRepository extends CrudRepository<Job, Long> {
	
	List<Job> findByTitle(String title);

	List<Job> findByEmployer(String employer);
	
	List<Job> findBySkills_Skill(String skill);
	
	List<Job> findAllBySkills(Skills skill);

	List<Job> findByPostedBy(String postedBy);
	
	List<Job> findBySkills_SkillId(Long skId);

}
