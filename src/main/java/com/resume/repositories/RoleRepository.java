package com.resume.repositories;

import org.springframework.data.repository.CrudRepository;

import com.resume.model.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

	Role findByRole(String string);

}
