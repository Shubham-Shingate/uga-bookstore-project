package com.uga.payment_detail_service.response;

import com.uga.payment_detail_service.model.PaymentCard;

public class SingleCardResponse {
	private String message;
	
	private Object apiError;
	
	private PaymentCard card;

	/**
	 * @param message
	 * @param apiError
	 * @param card
	 */
	public SingleCardResponse(String message, Object apiError, PaymentCard card) {
		this.message = message;
		this.apiError = apiError;
		this.card = card;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the apiError
	 */
	public Object getApiError() {
		return apiError;
	}

	/**
	 * @param apiError the apiError to set
	 */
	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	/**
	 * @return the card
	 */
	public PaymentCard getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(PaymentCard card) {
		this.card = card;
	}
	
	
}
