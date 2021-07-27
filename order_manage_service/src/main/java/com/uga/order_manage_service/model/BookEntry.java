package com.uga.order_manage_service.model;

public class BookEntry {
	
	private Long bookId;
	
	private Long quantity;

		
	/**
	 * 
	 */
	public BookEntry() {
	}


	/**
	 * @param bookId
	 * @param quantity
	 */
	public BookEntry(Long bookId, Long quantity) {
		this.bookId = bookId;
		this.quantity = quantity;
	}


	/**
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}


	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}


	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
	

}
