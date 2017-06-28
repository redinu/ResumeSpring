package com.resume.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository  extends CrudRepository<Person,Long>{
	
	Person findPersonByEmail(String email);
	Person findPersonByPersonId(Long personId);

}
