package com.uga.email_service.service;

import java.util.List;

public interface EmailService {

	public void sendEmail(List<String> recipients, String subject, String message);

	public void sendEmail(String recipient, String subject, String message);

}
