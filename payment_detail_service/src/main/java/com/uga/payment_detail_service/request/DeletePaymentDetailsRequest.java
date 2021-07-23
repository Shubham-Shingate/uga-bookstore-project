package com.uga.payment_detail_service.request;

import javax.validation.constraints.NotBlank;

public class DeletePaymentDetailsRequest {
	
	@NotBlank(message = "{cardNumber.notblank}")
	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public DeletePaymentDetailsRequest(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public DeletePaymentDetailsRequest() {
	
	}

	@Override
	public String toString() {
		return "DeletePaymentDetailsRequest [cardNumber=" + cardNumber + "]";
	}
	
	
	
	
}
