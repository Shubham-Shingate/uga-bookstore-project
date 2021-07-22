package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.forwords.model.ActiveUser;

@Repository
public interface ActiveUserRepository extends CrudRepository<ActiveUser, String> {
	
	public ActiveUser findByAccountId(String accountId);
	
	public ActiveUser findByEmailId(String emailId);
	
	public ActiveUser findByEmailIdOrAccountId(String emailId, String accountId);

	
}
