package com.uga.forwords.request;

import javax.validation.constraints.NotBlank;
import com.uga.forwords.validator.ValidEmail;

public class ForgotPasswordRequest {
	
	@ValidEmail
	@NotBlank(message = "{emailId.notblank}")
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public ForgotPasswordRequest(String emailId) {
		this.emailId = emailId;
	}
	
	public ForgotPasswordRequest() {
		
	}

	@Override
	public String toString() {
		return "ForgotPasswordRequest [emailId=" + emailId + "]";
	}
	
	
}
