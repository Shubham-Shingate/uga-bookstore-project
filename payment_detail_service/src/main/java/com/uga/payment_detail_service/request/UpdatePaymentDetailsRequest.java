package com.uga.payment_detail_service.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UpdatePaymentDetailsRequest {
	
	@NotBlank(message = "{cardNumber.notblank}")
	private String cardNumber;
	
	private String nameOnCard;
	
	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{2})$", message = "{cardExpiry.pattern}")
    private String cardExpiry;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public UpdatePaymentDetailsRequest(String cardNumber, String nameOnCard, String cardExpiry) {
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cardExpiry = cardExpiry;
	}

	public UpdatePaymentDetailsRequest() {
		
	}

	@Override
	public String toString() {
		return "UpdatePaymentDetailsRequest [cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", cardExpiry="
				+ cardExpiry + "]";
	}
	
}
