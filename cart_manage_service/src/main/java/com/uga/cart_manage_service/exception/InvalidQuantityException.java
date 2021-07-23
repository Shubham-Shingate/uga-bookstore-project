package com.uga.cart_manage_service.exception;

public class InvalidQuantityException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7651193726998670400L;

	public InvalidQuantityException(String message) {
		super(message);
	}
}
