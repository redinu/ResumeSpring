package com.resume.repositories;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Experience;

public interface ExperienceRepository extends CrudRepository<Experience, Long> {

}
