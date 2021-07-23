package com.uga.payment_detail_service.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.uga.payment_detail_service.request.UpdatePaymentDetailsRequest;
import com.uga.payment_detail_service.request.AddPaymentDetailsRequest;
import com.uga.payment_detail_service.request.DeletePaymentDetailsRequest;
import com.uga.payment_detail_service.response.PaymentDetailsResponse;
import com.uga.payment_detail_service.service.CardRepository;

@RestController
public class PaymentStorageController {
	
	@Autowired
	private CardRepository cardRepository;
	
	
	/* -------------------------------------Add new card to account------------------------------------------- */
	
	@PostMapping("/addPaymentDetails")
	public ResponseEntity<PaymentDetailsResponse> addPaymentDetails(@RequestHeader String accountId,
																	@RequestBody @Validated AddPaymentDetailsRequest addPaymentDetailsReq) throws ParseException {
		
		if (cardRepository.findByCardNumber(addPaymentDetailsReq.getCardNumber()) != null) {
			throw new CardExistException("Card is already in use!");
		}
		
		Date cardExpiry = (new SimpleDateFormat("MM/yy")).parse(addPaymentDetailsReq.getCardExpiry());
		
		PaymentCard paymentCard = new PaymentCard(addPaymentDetailsReq.getCardNumber(), addPaymentDetailsReq.getNameOnCard(), addPaymentDetailsReq.getCardType(),
				addPaymentDetailsReq.getCvv(), cardExpiry, addPaymentDetailsReq.getStatus() , accountId);
		cardRepository.save(paymentCard);
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Success", null, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.OK);
	}
	
	
	/* -------------------------------------Fetch cards attached to this account------------------------------------------- */

	@GetMapping("/getPaymentDetails")
	public ResponseEntity<PaymentDetailsResponse> getPaymentDetails(@RequestHeader String accountId) {
		List<PaymentCard> cardsFound = cardRepository.findByAccountId(accountId);
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Success", null, cardsFound);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.OK);
	}
	
	
	/* -------------------------------------Update card details: name and/or expiry date------------------------------------------- */
 
	@PostMapping("/updatePaymentDetails")
	public ResponseEntity<PaymentDetailsResponse> updatePaymentDetails(@RequestHeader String accountId,
																	   @RequestBody @Validated UpdatePaymentDetailsRequest updatePaymentDetailsRequest) throws ParseException {
		
		PaymentCard temp = cardRepository.findByCardNumberAndAccountId(updatePaymentDetailsRequest.getCardNumber(), accountId);
		if(temp == null) {
			throw new CardNotFoundException("You don't have this card!");
		}

		temp.setName(updatePaymentDetailsRequest.getNameOnCard());
		Date cardExpiry = (new SimpleDateFormat("MM/yy")).parse(updatePaymentDetailsRequest.getCardExpiry());
		temp.setCardExpiry(cardExpiry);
		cardRepository.save(temp);
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Success", null, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.OK);
	}
	
	
	/* -------------------------------------Delete existing card from given account given the card number------------------------------------------- */
	
	@PostMapping("/deletePaymentDetails")
	public ResponseEntity<PaymentDetailsResponse> deletePaymentDetails(@RequestHeader String accountId, @RequestBody @Validated DeletePaymentDetailsRequest deletePaymentDetailsRequest) {
		PaymentCard temp = cardRepository.findByCardNumberAndAccountId(deletePaymentDetailsRequest.getCardNumber(), accountId);
		
		// See if the user has a card with these details
		if(temp == null)
		{
			throw new CardNotFoundException("You don't have this card!");
		}
		
		// If they do, delete it
		cardRepository.delete(temp);
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Success", null, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.OK);
	}
	
}
