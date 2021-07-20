package com.uga.book_manage_service.exception;

public class DBAccessFailedException extends RuntimeException {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6665690141498754459L;

	public DBAccessFailedException(String description) {
		super(description);
	}

}
