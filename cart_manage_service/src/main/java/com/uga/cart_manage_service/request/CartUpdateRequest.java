package com.uga.cart_manage_service.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;

public class CartUpdateRequest {
	
	@NotNull(message = "{bookId.notnull}")
	private Long bookId;
	
	@NotNull(message = "{qty.notnull}")
	private Integer qty;

	/**
	 * 
	 */
	public CartUpdateRequest() {
	}

	/**
	 * @param bookId
	 * @param qty
	 */
	public CartUpdateRequest(@NotNull(message = "{bookId.notnull}") Long bookId,
			@NotNull(message = "{qty.notnull}") Integer qty) {
		this.bookId = bookId;
		this.qty = qty;
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
	 * @return the qty
	 */
	public Integer getQty() {
		return qty;
	}

	/**
	 * @param qty the qty to set
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}

	
	
	

}
