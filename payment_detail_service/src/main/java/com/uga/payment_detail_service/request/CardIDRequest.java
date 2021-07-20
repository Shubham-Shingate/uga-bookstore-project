package com.uga.payment_detail_service.request;

import javax.validation.constraints.NotBlank;

public class CardIDRequest {

	@NotBlank(message = "cardNumber.notblank")
	private String cardNumber;

	/**
	 * @param cardNumber
	 */
	public CardIDRequest(@NotBlank(message = "cardNumber.notblank") String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * 
	 */
	public CardIDRequest() {
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	

}
