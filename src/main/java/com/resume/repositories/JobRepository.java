package com.resume.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Job;

public interface JobRepository extends CrudRepository<Job, Long> {
	
	List<Job> findByTitle(String title);

	List<Job> findByEmployer(String employer);

}
