package com.uga.validate_token_service.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.uga.validate_token_service.exception.ExpiredTokenException;
import com.uga.validate_token_service.exception.InactiveTokenException;
import com.uga.validate_token_service.exception.TokenNotFoundException;
import com.uga.validate_token_service.model.ValidationToken;
import com.uga.validate_token_service.request.ValidateTokenRequest;
import com.uga.validate_token_service.response.ValidateTokenResponse;
import com.uga.validate_token_service.service.TokenRepository;

@RestController
public class ValidateTokenController {
	
	@Autowired
	private TokenRepository repository;

	/* Validate token */
	@PostMapping("/validateToken")
	public ResponseEntity<ValidateTokenResponse> validateToken(@RequestHeader("accountId") String accountId, @RequestBody ValidateTokenRequest request) {
		/* Check if token exists */
		ValidationToken token = repository.findByAccountIdAndValidationToken(accountId, request.getValidationToken());
		if(token == null)
			throw new TokenNotFoundException("The given validation token does not exist for this account");
		
		/* If exists, ensure status = active */
		if(token.getStatus().compareTo("ACTIVE") != 0)
			throw new InactiveTokenException("The given token is not active");
		
		/* If exists, ensure is not expired */
		Date currentDate = new Date(); 
		if(token.getExpiryDateTime().after(currentDate))
			throw new ExpiredTokenException("The given token is expired");
		
		
		/* If valid, set status = void, and return success */
		token.setStatus("VOID");
		repository.save(token);
		
		
		ValidateTokenResponse response = new ValidateTokenResponse("Success", null);
		return new ResponseEntity<ValidateTokenResponse>(response, HttpStatus.OK);
	}
}
