package com.uga.validate_token_service.exception;

public class InactiveTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InactiveTokenException(String message) {
		super(message);
	}

}
