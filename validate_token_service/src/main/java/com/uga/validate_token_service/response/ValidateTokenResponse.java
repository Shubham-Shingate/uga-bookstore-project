package com.uga.validate_token_service.response;

public class ValidateTokenResponse {
	
	private String message;
	
	private Object apiError;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getApiError() {
		return apiError;
	}

	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	public ValidateTokenResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
	}

	public ValidateTokenResponse() {
		
	}

	@Override
	public String toString() {
		return "ValidateTokenResponse [message=" + message + ", apiError=" + apiError + "]";
	}

	

}
