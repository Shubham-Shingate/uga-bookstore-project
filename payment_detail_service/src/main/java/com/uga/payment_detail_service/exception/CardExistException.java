package com.uga.payment_detail_service.exception;

public class CardExistException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardExistException(String description) {
		super(description);
	}

}
