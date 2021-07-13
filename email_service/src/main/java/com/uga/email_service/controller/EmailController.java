package com.uga.email_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.uga.email_service.request.EmailRequest;
import com.uga.email_service.response.EmailResponse;
import com.uga.email_service.service.EmailService;

@RestController
public class EmailController {

	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/emailService")
	public ResponseEntity<EmailResponse> emailService (@RequestBody @Validated EmailRequest emailRequest) {
		
		//Send the email
		emailService.sendEmail(emailRequest.getEmailAddress(), emailRequest.getSubject(), emailRequest.getEmailBody());
		
		//Log the email to the DB to store all the emails send by this service
		
		
		//Return a response from the service
		EmailResponse emailResponse = new EmailResponse("Success", null);
		return new ResponseEntity<EmailResponse>(emailResponse, HttpStatus.OK);	
	}

}
