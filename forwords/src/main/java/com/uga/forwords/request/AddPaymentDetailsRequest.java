package com.uga.forwords.request;

public class AddPaymentDetailsRequest {
	
	private String cardNumber;
	
	private String nameOnCard;
	
	private String cardType;
	
	private String cvv;
	
	private String cardExpiry;
	
	private String status;

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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddPaymentDetailsRequest(String cardNumber, String nameOnCard, String cardType, String cvv,
			String cardExpiry, String status) {
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cardType = cardType;
		this.cvv = cvv;
		this.cardExpiry = cardExpiry;
		this.status = status;
	}
	
	public AddPaymentDetailsRequest() {
		
	}

	@Override
	public String toString() {
		return "AddPaymentDetailsRequest [cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", cardType="
				+ cardType + ", cvv=" + cvv + ", cardExpiry=" + cardExpiry + ", status=" + status + "]";
	}
	

}
