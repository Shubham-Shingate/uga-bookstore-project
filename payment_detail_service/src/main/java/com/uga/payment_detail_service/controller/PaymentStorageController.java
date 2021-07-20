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
import com.uga.payment_detail_service.request.CardIDRequest;
import com.uga.payment_detail_service.request.CardRequest;
import com.uga.payment_detail_service.response.PaymentResponse;
import com.uga.payment_detail_service.response.SingleCardResponse;
import com.uga.payment_detail_service.service.CardRepository;

@RestController
public class PaymentStorageController {
	
	@Autowired
	private CardRepository cardRepository;
	
	// Add new card to account
	@PostMapping("/addPaymentCard")
	public ResponseEntity<PaymentResponse> addPaymentCard(@RequestHeader("accountId") String accountId, @RequestBody @Validated CardRequest request) {
		if(cardRepository.findByCardNumber(request.getCardNumber()) != null) {
			throw new CardExistException("Card is already in use!");
		}
		
		// Construct Card object
		PaymentCard card = new PaymentCard(request.getCardNumber(), request.getNameOnCard(), request.getCardType(), request.getCvv(), request.getExpiryTimestamp(), request.getStatus(), accountId);
		
		cardRepository.save(card);
		PaymentResponse response = new PaymentResponse("Success", null, null);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	
	// Fetch cards attached to this account
	@PostMapping("/fetchPaymentDetails")
	public ResponseEntity<PaymentResponse> fetchPaymentDetails(@RequestHeader("accountId") String accountId) {
		List<PaymentCard> cards = cardRepository.findByAccountId(accountId);
		PaymentResponse response = new PaymentResponse("Success", null, cards);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/fetchCard")
	public ResponseEntity<SingleCardResponse> fetchCard(@RequestHeader("accountId") String accountId, @RequestBody @Validated CardIDRequest request) {
		PaymentCard card = cardRepository.findByCardNumberAndAccountId(request.getCardNumber(), accountId);
		
		if(card == null)
			throw new CardNotFoundException("You do not have a card in our system with that number");
		
		SingleCardResponse response = new SingleCardResponse("Success", null, card);
		return new ResponseEntity<SingleCardResponse>(response, HttpStatus.OK);
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
		
		PaymentResponse response = new PaymentResponse("Success", null);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
	// Delete existing card from given account given the card number
	@PostMapping("/deletePaymentCard")
	public ResponseEntity<PaymentResponse> deletePaymentCard(@RequestHeader("accountId") String accountId, @RequestBody CardIDRequest request) {
		PaymentCard temp = cardRepository.findByCardNumberAndAccountId(request.getCardNumber(), accountId);
		
		// See if the user has a card with these details
		if(temp == null)
		{
			throw new CardNotFoundException("You don't have this card!");
		}
		
		// If they do, delete it
		cardRepository.delete(temp);
		PaymentResponse response = new PaymentResponse("Success", null);
		return new ResponseEntity<PaymentResponse>(response, HttpStatus.OK);
	}
	
}
