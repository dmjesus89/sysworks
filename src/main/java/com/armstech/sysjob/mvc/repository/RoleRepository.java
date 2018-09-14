package com.armstech.sysjob.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import com.armstech.sysjob.mvc.model.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

}
