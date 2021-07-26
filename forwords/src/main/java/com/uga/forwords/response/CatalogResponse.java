package com.uga.forwords.response;

import java.util.List;

import com.uga.forwords.model.Book;

public class CatalogResponse {
	private String message;
	
	private Object apiError;
	
	private List<Book> books;

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
	
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public CatalogResponse(String message, Object apiError, List<Book> books) {
		this.message = message;
		this.apiError = apiError;
		this.books = books;
	}
	
	public CatalogResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
		this.books = null;
	}

	public CatalogResponse() {
		
	}

	@Override
	public String toString() {
		String response = "EmailResponse [message=" + message + "apiError=" + apiError + "books=" + books +"]";
		
		return response;
	}	
}
