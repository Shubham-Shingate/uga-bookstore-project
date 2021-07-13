package com.uga.email_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(List<String> recipients, String subject, String message) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo((String[]) recipients.toArray());
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);
		return;
	}

	@Override
	public void sendEmail(String recipient, String subject, String message) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(recipient);
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);
		return;
	}
	
	
}
