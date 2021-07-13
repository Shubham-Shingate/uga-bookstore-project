package com.uga.email_service.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmailRequest {
	
	@NotBlank(message = "{subject.notblank}")
	private String subject;
	
	@NotBlank(message = "{emailBody.notblank}")
	private String emailBody;
	
	@NotBlank(message = "{emailAddress.notblank}")
	@Pattern(regexp = "^(.+)@(.+)$", message = "{emailAddress.pattern}")
	private String emailAddress;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public EmailRequest(String subject, String emailBody, String emailAddress) {
		this.subject = subject;
		this.emailBody = emailBody;
		this.emailAddress = emailAddress;
	}
	
	public EmailRequest() {
		
	}

	@Override
	public String toString() {
		return "EmailRequest [subject=" + subject + ", emailBody=" + emailBody + ", emailAddress=" + emailAddress + "]";
	}
	
	
}
