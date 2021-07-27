
package com.uga.validate_token_service.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.validate_token_service.model.VerificationToken;

@Repository
public interface TokenRepository extends CrudRepository <VerificationToken, Long> {
	
	@Transactional
	public VerificationToken findByAccountIdAndVerificationToken(String accountId, String verificationToken);
	
}
