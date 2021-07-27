package com.uga.profile_detail_service.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.profile_detail_service.model.ActiveUserDetails;

@Repository
public interface ActiveUserRepository extends CrudRepository<ActiveUserDetails, Long> {
	
	@Transactional
	public ActiveUserDetails findByAccountId(String accountId);
	
	@Transactional
	public ActiveUserDetails findByAccountIdAndPassword(String accountId, String oldPassword);

	
}
