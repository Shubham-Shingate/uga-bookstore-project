package com.uga.cart_manage_service.exception;

public class BookQtyInsufficientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BookQtyInsufficientException(String message) {
		super(message);
	}
	
}
