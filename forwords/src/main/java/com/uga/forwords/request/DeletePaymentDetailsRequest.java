package com.uga.forwords.request;

public class DeletePaymentDetailsRequest {
	
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
