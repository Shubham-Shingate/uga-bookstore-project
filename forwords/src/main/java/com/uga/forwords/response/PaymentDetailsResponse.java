package com.uga.forwords.response;

import java.util.List;

import com.uga.forwords.model.PaymentCard;

public class PaymentDetailsResponse {
	
	private String message;
	
	private Object apiError;
	
	private List<PaymentCard> cards;

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

	public PaymentDetailsResponse(String message, Object apiError, List<PaymentCard> cards) {
		this.message = message;
		this.apiError = apiError;
		this.cards = cards;
	}
	
	public PaymentDetailsResponse() {
		
	}

	@Override
	public String toString() {
		return "PaymentDetailsResponse [message=" + message + ", apiError=" + apiError + ", cards=" + cards + "]";
	}
	

}
