package com.uga.search_book_service.response;

import java.util.List;

import com.uga.search_book_service.model.Book;

public class SearchBookResponse {
	
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

	public SearchBookResponse(String message, Object apiError, List<Book> books) {
		this.message = message;
		this.apiError = apiError;
		this.books = books;
	}
	
	public SearchBookResponse() {
		
	}

	@Override
	public String toString() {
		return "SearchBookResponse [message=" + message + ", apiError=" + apiError + ", books=" + books + "]";
	}

	
	
}
