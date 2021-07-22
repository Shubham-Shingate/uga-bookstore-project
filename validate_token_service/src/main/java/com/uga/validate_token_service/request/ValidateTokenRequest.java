package com.uga.validate_token_service.request;

import javax.validation.constraints.NotBlank;

public class ValidateTokenRequest {

	@NotBlank(message="{validationToken.notblank}")
	private String validationToken;
		
	public ValidateTokenRequest() {
	}

	/**
	 * @param validationToken
	 */
	public ValidateTokenRequest(String validationToken) {
		this.validationToken = validationToken;
	}

	/**
	 * @return the validationToken
	 */
	public String getValidationToken() {
		return validationToken;
	}

	/**
	 * @param validationToken the validationToken to set
	 */
	public void setValidationToken(String validationToken) {
		this.validationToken = validationToken;
	}
	
	
	
}
