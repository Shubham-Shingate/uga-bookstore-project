package com.uga.validate_token_service.request;

import javax.validation.constraints.NotBlank;

public class ValidateTokenRequest {

	@NotBlank(message="{validationToken.notblank}")
	private String validationToken;

	public String getValidationToken() {
		return validationToken;
	}

	public void setValidationToken(String validationToken) {
		this.validationToken = validationToken;
	}

	public ValidateTokenRequest(String validationToken) {
		this.validationToken = validationToken;
	}
		
	public ValidateTokenRequest() {
		
	}

	@Override
	public String toString() {
		return "ValidateTokenRequest [validationToken=" + validationToken + "]";
	}
	
	
	
	
}
