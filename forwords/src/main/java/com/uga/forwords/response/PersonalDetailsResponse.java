package com.uga.forwords.response;

import com.uga.forwords.model.ActiveUserDetails;

public class PersonalDetailsResponse {
	
	private String message;
	
	private Object apiError;
	
	private ActiveUserDetails userDetails;

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

	public ActiveUserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(ActiveUserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public PersonalDetailsResponse(String message, Object apiError, ActiveUserDetails userDetails) {
		this.message = message;
		this.apiError = apiError;
		this.userDetails = userDetails;
	}
	
	public PersonalDetailsResponse() {
		
	}

	@Override
	public String toString() {
		return "PersonalDetailsResponse [message=" + message + ", apiError=" + apiError + ", userDetails=" + userDetails
				+ "]";
	}
	
}
