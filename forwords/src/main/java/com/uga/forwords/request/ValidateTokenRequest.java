package com.uga.forwords.request;

public class ValidateTokenRequest {

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
