package com.uga.validate_token_service.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.uga.validate_token_service.exception.ExpiredTokenException;
import com.uga.validate_token_service.exception.InactiveTokenException;
import com.uga.validate_token_service.exception.TokenNotFoundException;
import com.uga.validate_token_service.model.VerificationToken;
import com.uga.validate_token_service.request.ValidateTokenRequest;
import com.uga.validate_token_service.response.ValidateTokenResponse;
import com.uga.validate_token_service.service.TokenRepository;

@RestController
public class ValidateTokenController {

	@Autowired
	private TokenRepository repository;

	@PostMapping("/validateToken")
	public ResponseEntity<ValidateTokenResponse> validateToken(@RequestHeader String accountId,
															   @RequestBody @Validated ValidateTokenRequest request) {
		
		//Check if token exists 	
		VerificationToken token = repository.findByAccountIdAndVerificationToken(accountId, request.getValidationToken());
		
		if (token == null) {
			throw new TokenNotFoundException("The given validation token does not exist for this account");
		}
			

		// If exists, ensure status = active
		if (!token.getStatus().equals("ACTIVE")) {
			throw new InactiveTokenException("The given token is VOID (already used)");
		}
		
		// If exists, ensure is not expired
		Date currentDate = new Date(System.currentTimeMillis());
		if (!token.getExpiryDatetime().after(currentDate)) {
			throw new ExpiredTokenException("The given token is expired");
		}
		
		// If valid, set status = void, and return success 
		token.setStatus("VOID");
		repository.save(token);
		
		ValidateTokenResponse response = new ValidateTokenResponse("Success", null);
		return new ResponseEntity<ValidateTokenResponse>(response, HttpStatus.OK);
		
	}
}
