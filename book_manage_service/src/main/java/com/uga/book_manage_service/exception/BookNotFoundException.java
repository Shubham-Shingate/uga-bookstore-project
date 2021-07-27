package com.uga.book_manage_service.exception;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String message) {
		super(message);
	}

}
