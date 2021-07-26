package com.uga.profile_detail_service.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.profile_detail_service.model.ActiveUserDetails;

@Repository
public interface ActiveUserRepository extends CrudRepository<ActiveUserDetails, Long> {
	
	public ActiveUserDetails findByAccountId(String accountId);
	
	public ActiveUserDetails findByAccountIdAndPassword(String accountId, String oldPassword);

	
}
