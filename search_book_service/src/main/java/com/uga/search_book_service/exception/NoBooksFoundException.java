package com.uga.search_book_service.exception;

public class NoBooksFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBooksFoundException(String description) {
		super(description);
	}
	
	
}
