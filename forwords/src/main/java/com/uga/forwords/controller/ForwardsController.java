package com.uga.forwords.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.uga.forwords.request.DeleteShipppingDetailsRequest;
import com.uga.forwords.request.EmailRequest;
import com.uga.forwords.request.ShippingInfoRequest;
import com.uga.forwords.response.ShippingInfoResponse;

@Controller
public class ForwardsController {
	
	@Autowired RestTemplate restTemplate;
	
	@GetMapping("/home")
	public String showHome() {
		
		return "home";
	}
	
	/*----------------- Shipping details ----------------------*/
	
	/* Show update shipping information form for a single entry
	 * UI should retain individual address data from previous getShippingDetails list 
	 * page and pre-fill form data using that information*/
	@GetMapping("/editProfile/showUpdateShippingForm")
	public String showUpdateShippingForm(Model theModel) {
				
		theModel.addAttribute("updateShippingDetails", new ShippingInfoRequest());
		
		return "shipping-form";
	}
	
	/* User has filled out a form 
	 * Frontend has designated the required fields and made sure they are filled
	 * They have clicked submit and now we are updating DB*/
	@GetMapping("/editProfile/processShippingForm")
	public String processUpdateShippingForm(Principal principal, Model theModel, 
			@Valid @ModelAttribute("updateShippingDetails") ShippingInfoRequest shippingRequest) {
		
		// Set account id in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Set up Http entity with request body
		HttpEntity<ShippingInfoRequest> entity = new HttpEntity<ShippingInfoRequest>(shippingRequest, headers);
		
		// Send request to backend service
		ResponseEntity<ShippingInfoResponse> shippingInfoResponse = restTemplate.postForEntity("http://shipping-detail-service/updateShippingDetails", entity, ShippingInfoResponse.class);
		
//		sendProfileChangeConfirmationEmail(principal.getName());
				
		return "shipping-form";
	}
	
	@GetMapping("/editProfile/getShippingDetails")
	public String showGetShippingDetails(Principal principal, Model theModel) {
		
		// Set account ID in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service
		HttpEntity<String> entity = new HttpEntity<String>(headers);
//		ResponseEntity<ShippingInfoResponse> existingInfo = restTemplate.getForEntity("http://shipping-detail-service/getShippingDetails", ShippingInfoResponse.class, entity);
		ResponseEntity<ShippingInfoResponse> existingInfo = restTemplate.exchange("http://shipping-detail-service/getShippingDetails", HttpMethod.GET, entity, ShippingInfoResponse.class);
		
		// Add information to model
		theModel.addAttribute("currentShippingInfo", existingInfo.getBody());
		
		return "shipping-form";
	}
	
	@GetMapping("/editProfile/deleteShippingDetails/{addressId}")
	public String showDeleteShippingDetails(Principal principal, @PathVariable long addressId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		DeleteShipppingDetailsRequest request = new DeleteShipppingDetailsRequest(addressId);
		HttpEntity<DeleteShipppingDetailsRequest> entity = new HttpEntity<DeleteShipppingDetailsRequest>(request, headers);
		restTemplate.postForEntity("http://shipping-detail-service/deleteShippingDetails", entity, ShippingInfoResponse.class);
		
//		sendProfileChangeConfirmationEmail(principal.getName());
		
		return "shipping-form";
	}
	
	public void sendProfileChangeConfirmationEmail(String accountId) {
		// Find email associated with this account
		// String emailAddress = 
		
		// Send confirmation email
		EmailRequest email = new EmailRequest();
//		email.setEmailAddress(emailAddress);
		email.setSubject("Account information has been updated");
		email.setEmailBody("Your account information has been changed. If you did not request this change, please contact customer support.");
		
		
	}
	
	
	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	

	
	
	
	
	
}
