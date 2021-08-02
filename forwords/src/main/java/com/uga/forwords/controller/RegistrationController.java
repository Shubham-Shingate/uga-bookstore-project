package com.uga.forwords.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.uga.forwords.model.ActiveUser;
import com.uga.forwords.model.Cart;
import com.uga.forwords.model.Config;
import com.uga.forwords.model.Role;
import com.uga.forwords.model.User;
import com.uga.forwords.model.VerificationToken;
import com.uga.forwords.request.AddPaymentDetailsRequest;
import com.uga.forwords.request.CreateAccountRequest;
import com.uga.forwords.request.EmailRequest;
import com.uga.forwords.request.ForgotPasswordRequest;
import com.uga.forwords.request.ResetPasswordRequest;
import com.uga.forwords.request.ShippingInfoRequest;
import com.uga.forwords.request.ValidateTokenRequest;
import com.uga.forwords.response.EmailResponse;
import com.uga.forwords.response.PaymentDetailsResponse;
import com.uga.forwords.response.ShippingInfoResponse;
import com.uga.forwords.response.ValidateTokenResponse;
import com.uga.forwords.service.ActiveUserRepository;
import com.uga.forwords.service.CartRepository;
import com.uga.forwords.service.ConfigRepository;
import com.uga.forwords.service.RoleRepository;
import com.uga.forwords.service.UserRepository;
import com.uga.forwords.service.VerificationTokenRepository;
import com.uga.forwords.util.SHAFactory;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ActiveUserRepository activeUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Autowired
	private VerificationTokenRepository verificationRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder; 
	
	private Map<String, String> applicationConfig = new HashMap<String, String>();
	
	@PostConstruct
	private void loadConfig() {
		List<Config> configs = (List<Config>) configRepository.findAll();
		for (Config config : configs) {
			applicationConfig.put(config.getConfigKey(), config.getConfigValue());
		}
	}
	
	/** -----------------------------------Service Endpoint for showing registration form----------------------------------- */
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		
		theModel.addAttribute("createAccountRequest", new CreateAccountRequest());
		return "sign-up";
	}
	
	/** -----------------------------------Service Endpoint for Processing User Registration----------------------------------- */
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("createAccountRequest") CreateAccountRequest createAccountRequest, BindingResult theBindingResult,
											 Model theModel) {
		
		//Check if Field Match generates any error for pasword and matchingPassword
		if (theBindingResult.hasErrors()) {
			return "sign-up";
		}
		
		/*
		 * Check the username (i.e email id) if already present in the database ACTIVE_USER_MASTER table. If emailId is present in USER_MASTER then but
		 *  not in ACTIVE_USER_MASTER still allow him to register with same email Id (new accounID will be generated).
		 */ 
		ActiveUser existingUser = activeUserRepository.findByEmailId(createAccountRequest.getEmailId());
		if (existingUser != null) {
			theModel.addAttribute("createAccountRequest", new CreateAccountRequest());
			theModel.addAttribute("registrationError", "User name already exists.");
			return "sign-up";
		}
		
		//Save the new user to the database with inactive status
		String accountId = userRepository.saveUser(createAccountRequest.getFullName(), createAccountRequest.getPhoneNumber(),
				createAccountRequest.getEmailId(), bcryptPasswordEncoder.encode(createAccountRequest.getPassword()), "NA", "ROLE_CUSTOMER");
		
		//Save users shipping details if provided (API call - shipping_details_service)
		if ((createAccountRequest.getStreet() != null && !createAccountRequest.getStreet().isEmpty()) &&
		    (createAccountRequest.getCity() != null && !createAccountRequest.getCity().isEmpty()) &&
		    (createAccountRequest.getState() != null && !createAccountRequest.getState().isEmpty()) &&
		    (createAccountRequest.getZipcode() != null && !createAccountRequest.getZipcode().isEmpty())) {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("accountId", accountId);

			ShippingInfoRequest shippingInfoRequest = new ShippingInfoRequest(createAccountRequest.getStreet(), createAccountRequest.getCity(),
																			  createAccountRequest.getState(), createAccountRequest.getZipcode(),"DEFAULT");
		
			HttpEntity<ShippingInfoRequest> entity = new HttpEntity<ShippingInfoRequest>(shippingInfoRequest, headers);
			
			ResponseEntity<ShippingInfoResponse> shippingDetailsServiceResponse = 
					restTemplate.postForEntity("http://shipping-detail-service/updateShippingDetails", entity, ShippingInfoResponse.class);
		}
		
		//Save users card details if provided (API call- payment_details_service)
		if((createAccountRequest.getCardNumber() != null && !createAccountRequest.getCardNumber().isEmpty())
				&& (createAccountRequest.getNameOnCard() != null && !createAccountRequest.getNameOnCard().isEmpty())
				&& (createAccountRequest.getCardType() != null && !createAccountRequest.getCardType().isEmpty())
				&& (createAccountRequest.getCvv() != null && !createAccountRequest.getCvv().isEmpty())
				&& (createAccountRequest.getCardExpiry() != null && !createAccountRequest.getCardExpiry().isEmpty())) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("accountId", accountId);
			
			String encryptedCVV = SHAFactory.getSHAEncryptedString(createAccountRequest.getCvv(), applicationConfig.get("SHA256_SALT").getBytes(), "SHA-256");
			AddPaymentDetailsRequest addPaymentDetailsRequest = new AddPaymentDetailsRequest(createAccountRequest.getCardNumber(),
					createAccountRequest.getNameOnCard(), createAccountRequest.getCardType(), encryptedCVV, createAccountRequest.getCardExpiry(), "DEFAULT");
			
			HttpEntity<AddPaymentDetailsRequest> entity = new HttpEntity<AddPaymentDetailsRequest>(addPaymentDetailsRequest, headers);
			
			ResponseEntity<PaymentDetailsResponse> paymentDetailsServiceResponse = 
					restTemplate.postForEntity("http://payment-detail-service/addPaymentDetails", entity, PaymentDetailsResponse.class);
		}
		
		//Create a verification SHA-256 token
		String verificationToken = SHAFactory.getSHAEncryptedString(accountId+System.currentTimeMillis(), applicationConfig.get("SHA256_SALT").getBytes(), "SHA-256");
		
		//Make an API call to email_service to send email verification link+token (token = SHA256{accountID+current_timestamp}). (API call- email_service)
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		EmailRequest emailRequest = new EmailRequest("ACCOUNT VERIFICATION LINK",
													applicationConfig.get("ACCOUNT_VERIFICATION_EMAIL") 
													+ applicationConfig.get("ACCOUNT_VERIFICATION_LINK")
													+"?verificationToken="+verificationToken+"&accountId="+accountId,
													createAccountRequest.getEmailId());
		HttpEntity<EmailRequest> entity = new HttpEntity<EmailRequest>(emailRequest, headers);
		ResponseEntity<EmailResponse> emailServiceResponse = 
					restTemplate.postForEntity("http://email-service/emailService", entity, EmailResponse.class);
		
		
		//On Success response from email_service we store verification token in DB VERICATION_TOKEN table
		if (emailServiceResponse.getStatusCode().equals(HttpStatus.OK) && emailServiceResponse.getBody().getMessage().equals("Success")) {
			
			Date now = new Date(System.currentTimeMillis());
			Long tokenExp = System.currentTimeMillis() + (Long.parseLong(applicationConfig.get("ACCOUNT_VERIFICATION_TOKEN_VALIDITY_MINS"))*60000); 
			Date expDate = new Date(tokenExp);
			
			VerificationToken verificationTokenEntity = new VerificationToken(null, verificationToken, now, expDate, accountId, "ACTIVE");
			verificationRepository.save(verificationTokenEntity);
			return "registration-confirmation";
		} else {
			theModel.addAttribute("registrationError", "Failed to send verfication email");
			theModel.addAttribute("createAccountRequest", new CreateAccountRequest());
			return "sign-up";
		}
	}
	
	
	
	/** -----------------------------------Service Endpoint for Account Verification----------------------------------- */

	@GetMapping("/accountVerification")
	public String accountVerification (@RequestParam String verificationToken, @RequestParam String accountId) {
		
		//API call to the validate_token_service
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", accountId);
		
		ValidateTokenRequest validateTokenRequest = new ValidateTokenRequest(verificationToken);
		HttpEntity<ValidateTokenRequest> entity = new HttpEntity<ValidateTokenRequest>(validateTokenRequest, headers);
		
		ResponseEntity<ValidateTokenResponse> validateTokenServiceResponse = restTemplate.postForEntity("http://validate-token-service/validateToken", entity, ValidateTokenResponse.class);
		
		if (validateTokenServiceResponse.getStatusCode().equals(HttpStatus.OK)
				&& validateTokenServiceResponse.getBody().getMessage().equals("Success")) {
			//Change the status of the user as active ("A")
			User verifiedUser = userRepository.findByAccountId(accountId);
			verifiedUser.setAccountStatus("A");
			userRepository.save(verifiedUser);
			
			//Now save the user in actual authentication/session related table ACTIVE_USER_MASTER. And create a User-Role Mapping
			Role role = roleRepository.findByRoleName("ROLE_CUSTOMER");
			
			ActiveUser activeUser = new ActiveUser(verifiedUser.getFullName(), verifiedUser.getPhoneNo(), verifiedUser.getEmailId(), verifiedUser.getPassword(),
					verifiedUser.getAccountId(), verifiedUser.getCreatedDatetime(), verifiedUser.getAccountStatus(), Arrays.asList(role));
			
			activeUserRepository.save(activeUser);
			
			//Now create the cart for the User
			Cart userCart = new Cart(accountId);
			cartRepository.save(userCart);
			
			//Send the ACCOUNT_ID to the user via email trigger -email_service
			HttpHeaders headers2 = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			EmailRequest emailRequest = new EmailRequest("UPDATE CONFIRMATION EMAIL", applicationConfig.get("ACCOUNT_ID_CONFIRMATION")+accountId, activeUser.getEmailId());
			HttpEntity<EmailRequest> entity2 = new HttpEntity<EmailRequest>(emailRequest, headers2);
			ResponseEntity<EmailResponse> emailServiceResponse = 
						restTemplate.postForEntity("http://email-service/emailService", entity2, EmailResponse.class);
			
			return "account-verification-confirmation";
		} else {
			return "account-verification-failed";
		}
		
	}
	
	
	/** -----------------------------------Service Endpoints for Forgot Password Process and Verification----------------------------------- */
	
	@GetMapping("/forgotPassword")
	public String forgotPassword (Model theModel) {
		
		theModel.addAttribute("forgotPasswordReq", new ForgotPasswordRequest());
		return "forgot-password"; // NOTE: Show the email ID enter form here
	}
	
	
	@PostMapping("/processForgotPasswordForm")
	public String processForgotPasswordForm(@Valid @ModelAttribute("forgotPasswordReq") ForgotPasswordRequest forgotPasswordRequest, BindingResult theBindingResult,
			 Model theModel) {
		
		// Check if Field generates any validation error
		if (theBindingResult.hasErrors()) {
			return "forgot-password";
		}
		
		//Check if the emailId given is a registered and verified user 
		ActiveUser existingUser = activeUserRepository.findByEmailId(forgotPasswordRequest.getEmailId());
		
		if (existingUser == null) {
			theModel.addAttribute("forgotPasswordReq", new ForgotPasswordRequest());
			theModel.addAttribute("forgotPassError", "User name/Email ID is not registered with us.");
			return "forgot-password";
		}
		
		// Create a verification SHA-256 token
		String verificationToken = SHAFactory.getSHAEncryptedString(existingUser.getAccountId() + System.currentTimeMillis(),
				applicationConfig.get("SHA256_SALT").getBytes(), "SHA-256");
		
		EmailRequest emailRequest = new EmailRequest("FORGOT PASSWORD VERIFICATION LINK",
				applicationConfig.get("FORGOT_PASSWORD_VERIFICATION_EMAIL") 
				+ applicationConfig.get("FORGOT_PASSWORD_VERIFICATION_LINK")
				+"?verificationToken="+verificationToken+"&accountId="+existingUser.getAccountId(),
				existingUser.getEmailId());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<EmailRequest> entity = new HttpEntity<EmailRequest>(emailRequest, headers);
		ResponseEntity<EmailResponse> emailServiceResponse = restTemplate.postForEntity("http://email-service/emailService", entity, EmailResponse.class);
		
		if (emailServiceResponse.getStatusCode().equals(HttpStatus.OK) && emailServiceResponse.getBody().getMessage().equals("Success")) {
			
			Date now = new Date(System.currentTimeMillis());
			Long tokenExp = System.currentTimeMillis() + (Long.parseLong(applicationConfig.get("FORGOT_PASSWORD_VERIFICATION_TOKEN_VALIDITY_MINS"))*60000); 
			Date expDate = new Date(tokenExp);
			
			VerificationToken verificationTokenEntity = new VerificationToken(null, verificationToken, now, expDate, existingUser.getAccountId(), "ACTIVE");
			verificationRepository.save(verificationTokenEntity);
			return "email-sent-confirmation";
		} else {
			theModel.addAttribute("forgotPasswordReq", new ForgotPasswordRequest());
			theModel.addAttribute("forgotPassError", "Failed to send verification email.");
			return "forgot-password";
		}	
	}
	
	@GetMapping("/forgotPasswordVerification")
	public String forgotPasswordVerification (@RequestParam String verificationToken, @RequestParam String accountId, Model theModel) {
		
		// API call to the validate_token_service
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", accountId);
		
		ValidateTokenRequest validateTokenRequest = new ValidateTokenRequest(verificationToken);
		HttpEntity<ValidateTokenRequest> entity = new HttpEntity<ValidateTokenRequest>(validateTokenRequest, headers);
		
		ResponseEntity<ValidateTokenResponse> validateTokenServiceResponse = restTemplate.postForEntity("http://validate-token-service/validateToken", entity, ValidateTokenResponse.class);
		
		if (validateTokenServiceResponse.getStatusCode().equals(HttpStatus.OK) && validateTokenServiceResponse.getBody().getMessage().equals("Success")) {
			
			theModel.addAttribute("resetPasswordReq", new ResetPasswordRequest());
			theModel.addAttribute("accountId", accountId); 
			return "reset-password-form";  //Note reset-password-form shows 2 fields -new password, re-enter new password. (both should be checked at frontend)
		} else {
			theModel.addAttribute("tokenVerificationError", ((LinkedHashMap<?, ?>) validateTokenServiceResponse.getBody().getApiError()).get("message"));
			return "redirect:/register/forgotPassword";
		}
		
	}
	
	
	@PostMapping("/processResetPasswordForm")
	public String processResetPasswordForm(@Valid @ModelAttribute("resetPasswordReq") ResetPasswordRequest resetPasswordReq,
											BindingResult theBindingResult, Model theModel) {
		
		if (theBindingResult.hasErrors()) {
			return "reset-password-form";
		}	
		//Save the new password against the given account ID
		ActiveUser activeUser = activeUserRepository.findByAccountId(resetPasswordReq.getAccountId());
		activeUser.setPassword(bcryptPasswordEncoder.encode(resetPasswordReq.getNewPassword()));
		activeUserRepository.save(activeUser);
		
		return "reset-password-confirmation";
	}

	

}
