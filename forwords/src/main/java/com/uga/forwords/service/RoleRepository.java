package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.forwords.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role findByRoleName(String roleName);
	
}
