/**
 * 
 */
package com.uga.validate_token_service.service;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.validate_token_service.model.ValidationToken;

@Repository
public interface TokenRepository extends CrudRepository<ValidationToken, Integer> {
	
	public ValidationToken findByAccountIdAndValidationToken(String accountId, String validationToken);
	
}
