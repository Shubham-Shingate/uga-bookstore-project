package com.uga.payment_detail_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.uga.payment_detail_service.exception.CardExistException;
import com.uga.payment_detail_service.exception.CardNotFoundException;
import com.uga.payment_detail_service.model.PaymentCard;
import com.uga.payment_detail_service.request.CardRequest;
import com.uga.payment_detail_service.response.PaymentResponse;
import com.uga.payment_detail_service.service.CardRepository;

@RestController
public class PaymentStorageController {
	
	@Autowired
	private CardRepository cardRepository;
	
	@GetMapping("/")
	public ResponseEntity<PaymentResponse> test() {
		PaymentResponse response = new PaymentResponse("Hello", null);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	// Add new card to account
	@PostMapping("/addPaymentCard")
	public ResponseEntity<PaymentResponse> addPaymentCard(@RequestHeader("accountId") String accountId, @RequestBody @Validated CardRequest request) {
		if(cardRepository.findByCardNumber(request.getCardNumber()) != null) {
			throw new CardExistException("Card is already in use!");
		}
		
		// Construct Card object
		PaymentCard card = new PaymentCard(request.getCardNumber(), request.getNameOnCard(), request.getCardType(), request.getCvv(), request.getExpiryTimestamp(), request.getStatus(), accountId);
		
		cardRepository.save(card);
		PaymentResponse response = new PaymentResponse("New card successfully added", null, null);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	
	// Fetch cards attached to this account
	@PostMapping("/fetchPaymentDetails")
	public ResponseEntity<PaymentResponse> fetchPaymentDetails(@RequestHeader("accountId") String accountId) {
		List<PaymentCard> cards = cardRepository.findByAccountId(accountId);
		PaymentResponse response = new PaymentResponse("Fetch success", null, cards);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	// Update card details: name and/or expiry date
	@PostMapping("/updatePaymentCard")
	public ResponseEntity<PaymentResponse> updatePaymentCard(@RequestHeader("accountId") String accountId, @RequestBody CardRequest request) {
		
		PaymentCard temp = cardRepository.findByCardNumberAndAccountId(request.getCardNumber(), accountId);
		
		// Check that they are the card owner in DB
		if(temp == null) {
			throw new CardNotFoundException("You don't have this card!");
		}
		
		// Update card entry with the allowable fields
		if(temp.getType().compareTo(request.getCardType()) == 0 && temp.getCvv().compareTo(request.getCvv()) == 0 && temp.getStatus().compareTo(request.getStatus()) == 0)
		{
			temp.setName(request.getNameOnCard());
			temp.setCardExpiry(request.getExpiryTimestamp());
			cardRepository.save(temp);
		}
		
		PaymentResponse response = new PaymentResponse("Card successfully updated!" + request + temp, null);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	// Delete existing card from given account given the card number
	@PostMapping("/deletePaymentCard")
	public ResponseEntity<PaymentResponse> deletePaymentCard(@RequestHeader("accountId") String accountId, @RequestBody @Validated CardRequest request) {
		PaymentCard temp = cardRepository.findByCardNumberAndAccountId(request.getCardNumber(), accountId);
		
		if(temp == null)
		{
			throw new CardNotFoundException("You don't have this card!");
		}		
		cardRepository.delete(temp);
		PaymentResponse response = new PaymentResponse("Deletion successful", null);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
}
