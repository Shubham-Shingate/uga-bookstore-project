package com.uga.cart_manage_service.exception;

public class EmptyCartException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyCartException(String message) {
		super(message);
	}

}
