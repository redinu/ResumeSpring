package com.resume.repositories;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Person;
import com.resume.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
	
	public User findUserByUsername(String username);
	public Boolean existsByUsername(String username);

}
