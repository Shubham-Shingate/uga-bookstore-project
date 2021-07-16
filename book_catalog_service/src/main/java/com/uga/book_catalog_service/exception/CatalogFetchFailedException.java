package com.uga.book_catalog_service.exception;

public class CatalogFetchFailedException extends RuntimeException {
	
	private static final long serialVersionUID = 2L;
	
	public CatalogFetchFailedException(String description) {
		super(description);
	}

}
