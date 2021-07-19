package com.uga.payment_detail_service.response;

import java.util.List;

import com.uga.payment_detail_service.model.PaymentCard;

public class PaymentResponse {
	
	private String message;
	
	private Object apiError;
	
	private List<PaymentCard> cards;

	public PaymentResponse(String message, Object apiError, List<PaymentCard> cards) {
		this.message = message;
		this.apiError = apiError;
		this.cards = cards;
	}
	
	public PaymentResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
		this.cards = null;
	}

	@Override
	public String toString() {
		return "PaymentResponse [message=" + message + ", apiError=" + apiError + ", cards=" + cards + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getApiError() {
		return apiError;
	}

	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	public List<PaymentCard> getCards() {
		return cards;
	}

	public void setCards(List<PaymentCard> cards) {
		this.cards = cards;
	}

}
