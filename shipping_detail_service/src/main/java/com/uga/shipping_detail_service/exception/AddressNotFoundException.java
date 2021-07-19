package com.uga.shipping_detail_service.exception;

public class AddressNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddressNotFoundException(String description) {
		super(description);
	}	
	
}
