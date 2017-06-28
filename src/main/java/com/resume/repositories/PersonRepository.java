package com.resume.repositories;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
