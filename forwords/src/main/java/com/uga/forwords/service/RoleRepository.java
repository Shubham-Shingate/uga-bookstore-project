package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.forwords.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	@Transactional
	public Role findByRoleName(String roleName);
	
}
