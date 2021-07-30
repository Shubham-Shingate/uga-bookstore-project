package com.uga.cart_manage_service.response;

import java.util.List;

import com.uga.cart_manage_service.model.CartBook;

public class CartResponse {

	private String message;

	private Object apiError;

	private List<CartBook> books;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getApiError() {
		return apiError;
	}

	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	public List<CartBook> getBooks() {
		return books;
	}

	public void setBooks(List<CartBook> books) {
		this.books = books;
	}

	public CartResponse(String message, Object apiError, List<CartBook> books) {
		this.message = message;
		this.apiError = apiError;
		this.books = books;
	}
	
	public CartResponse() {
		
	}

	@Override
	public String toString() {
		return "CartResponse [message=" + message + ", apiError=" + apiError + ", books=" + books + "]";
	}


}
