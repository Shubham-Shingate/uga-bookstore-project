package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.forwords.model.ActiveUser;

@Repository
public interface ActiveUserRepository extends CrudRepository<ActiveUser, String> {
	
	@Transactional
	public ActiveUser findByAccountId(String accountId);
	
	@Transactional
	public ActiveUser findByEmailId(String emailId);
	
	@Transactional
	public ActiveUser findByEmailIdOrAccountId(String emailId, String accountId);

	
}
