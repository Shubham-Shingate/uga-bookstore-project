package com.uga.profile_detail_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.uga.profile_detail_service.exception.AccountNotFoundException;
import com.uga.profile_detail_service.model.ActiveUserDetails;
import com.uga.profile_detail_service.request.ChangePasswordRequest;
import com.uga.profile_detail_service.request.UpdateProfileDetailsRequest;
import com.uga.profile_detail_service.response.PersonalDetailsResponse;
import com.uga.profile_detail_service.service.ActiveUserRepository;

@RestController
public class ProfileDetailController {
	
	@Autowired
	private ActiveUserRepository activeUserRepository; 
	
	@GetMapping("/getPersonalDetails")
	public ResponseEntity<PersonalDetailsResponse> getPersonalDetails(@RequestHeader String accountId) {
		
		ActiveUserDetails activeUser = activeUserRepository.findByAccountId(accountId);
		if (activeUser == null) {
			throw new AccountNotFoundException("No account found for given accountId");
		}
		PersonalDetailsResponse personalDetailsResponse = new PersonalDetailsResponse("Success", null, activeUser);
		return new ResponseEntity<PersonalDetailsResponse>(personalDetailsResponse, HttpStatus.OK);
		
	}
	
	@PostMapping("/updatePersonalDetails")
	public ResponseEntity<PersonalDetailsResponse> updatePersonalDetails(@RequestHeader String accountId, @Validated @RequestBody UpdateProfileDetailsRequest updateProfileDetailsRequest) {
		
		ActiveUserDetails activeUser = activeUserRepository.findByAccountId(accountId);
		if (activeUser == null) {
			throw new AccountNotFoundException("No account found for given accountId");
		}
		activeUser.setFullName(updateProfileDetailsRequest.getFullName());
		activeUser.setPhoneNo(updateProfileDetailsRequest.getPhoneNo());
		activeUserRepository.save(activeUser);
		
		PersonalDetailsResponse personalDetailsResponse = new PersonalDetailsResponse("Success", null, null);
		return new ResponseEntity<PersonalDetailsResponse>(personalDetailsResponse, HttpStatus.OK);
	
	}
	
	
	@GetMapping("/togglePromotionSubscription/{toggleValue}")
	public ResponseEntity<PersonalDetailsResponse> togglePromotions(@RequestHeader String accountId, @PathVariable Long toggleValue) {
		
		ActiveUserDetails activeUser = activeUserRepository.findByAccountId(accountId);
		if (activeUser == null) {
			throw new AccountNotFoundException("No account found for given accountId");
		}
		activeUser.setSubscribed(toggleValue);
		activeUserRepository.save(activeUser);
		
		PersonalDetailsResponse personalDetailsResponse = new PersonalDetailsResponse("Success", null, null);
		return new ResponseEntity<PersonalDetailsResponse>(personalDetailsResponse, HttpStatus.OK);
	
	}
	
	@PostMapping("/changePassword")  //Note: This endpoint accepts the oldPassword in encoded form. So sent it directly in encoded form
	public ResponseEntity<PersonalDetailsResponse> changePassword(@RequestHeader String accountId, @Validated @RequestBody ChangePasswordRequest changePasswordRequest) {
		
		ActiveUserDetails activeUser = activeUserRepository.findByAccountIdAndPassword(accountId, changePasswordRequest.getOldPassword());		
		if (activeUser == null) {
			throw new AccountNotFoundException("No account found for given accountId and Password combination");
		}
		activeUser.setPassword(changePasswordRequest.getNewPassword());
		activeUserRepository.save(activeUser);
	
		PersonalDetailsResponse personalDetailsResponse = new PersonalDetailsResponse("Success", null, null);
		return new ResponseEntity<PersonalDetailsResponse>(personalDetailsResponse, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
