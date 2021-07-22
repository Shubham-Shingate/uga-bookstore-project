package com.uga.validate_token_service.response;

public class ValidateTokenResponse {
	
	private String message;
	
	private Object apiError;

	/**
	 * @param message
	 * @param apiError
	 */
	public ValidateTokenResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the apiError
	 */
	public Object getApiError() {
		return apiError;
	}

	/**
	 * @param apiError the apiError to set
	 */
	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}
	
	

}
