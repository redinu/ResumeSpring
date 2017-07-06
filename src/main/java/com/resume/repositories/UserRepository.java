package com.resume.repositories;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Person;
import com.resume.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
	
	User findByUsername(String username);
	Boolean existsByUsername(String username);
	User findByEmail(String email);
	Long countByEmail(String email);
	Long countByUsername(String username);
}
