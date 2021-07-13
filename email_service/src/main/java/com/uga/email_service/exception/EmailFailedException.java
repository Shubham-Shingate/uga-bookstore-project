package com.uga.email_service.exception;

public class EmailFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailFailedException(String description) {
		super(description);
	}
}
