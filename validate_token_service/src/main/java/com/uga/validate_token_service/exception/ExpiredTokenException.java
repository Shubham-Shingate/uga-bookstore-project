package com.uga.validate_token_service.exception;

public class ExpiredTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpiredTokenException(String message) {
		super(message);
	}

}
