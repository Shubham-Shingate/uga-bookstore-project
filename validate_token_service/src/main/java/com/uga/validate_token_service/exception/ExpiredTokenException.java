package com.uga.validate_token_service.exception;

public class ExpiredTokenException extends RuntimeException {

	private static final long serialVersionUID = -4426320662444032791L;

	public ExpiredTokenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
