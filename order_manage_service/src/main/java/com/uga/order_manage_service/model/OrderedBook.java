package com.uga.order_manage_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(OrderedBookId.class)
@Entity
@Table(name = "ORDER_BOOK_MAPPING")
public class OrderedBook {

	@Id
	@Column(name="BOOK_ID")
	private Long bookId;
	
	@Id
	@Column(name="ORDER_ID")
	private String orderId;
		
	@Column(name="QUANTITY")
	private Long quantity;

	/**
	 * 
	 */
	public OrderedBook() {
	}

	/**
	 * @param orderId
	 * @param bookId
	 * @param quantity
	 */
	public OrderedBook(String orderId, Long bookId, Long quantity) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
