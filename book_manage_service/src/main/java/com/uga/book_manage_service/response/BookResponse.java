package com.uga.book_manage_service.response;

import com.uga.book_manage_service.model.Book;

public class BookResponse {
	
	private String message;
	
	private Object apiError;
	
	private Book book;

	public BookResponse() {
	}

	public BookResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
		this.book = null;
	}

	public BookResponse(String message, Object apiError, Book book) {
		this.message = message;
		this.apiError = apiError;
		this.book = book;
	}

	@Override
	public String toString() {
		return "BookResponse [message=" + message + ", apiError=" + apiError + ", book=" + book + "]";
	}

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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	
}
