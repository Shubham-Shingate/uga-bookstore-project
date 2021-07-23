
package com.uga.validate_token_service.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.validate_token_service.model.VerificationToken;

@Repository
public interface TokenRepository extends CrudRepository <VerificationToken, Long> {
	
	public VerificationToken findByAccountIdAndVerificationToken(String accountId, String verificationToken);
	
}
