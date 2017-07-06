package com.resume.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Person;
import com.resume.model.User;

public interface PersonRepository  extends CrudRepository<Person,Long>{
	
	Person findPersonByEmail(String email);
	List<Person> findByFirstName(String firstName);
	Person findPersonByPersonId(Long personId);

}
