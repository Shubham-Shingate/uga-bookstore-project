package com.uga.email_service.response;

public class EmailResponse {
	
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

	public EmailResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
	}

	public EmailResponse() {
		
	}

	@Override
	public String toString() {
		return "EmailResponse [message=" + message + ", apiError=" + apiError + "]";
	}	
		
}
