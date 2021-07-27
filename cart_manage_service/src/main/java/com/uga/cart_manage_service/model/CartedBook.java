package com.uga.cart_manage_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(CartBookId.class)
@Entity 
@Table(name = "CART_BOOK_MAPPING")
public class CartedBook {
	
	@Id
	@Column(name="CART_ID")
	private long cartId;
	
	@Id
	@Column(name="BOOK_ID")
	private long bookId;
	
	@Column(name="QUANTITY")
	private int quantity;

	/**
	 * 
	 */
	public CartedBook() {
	}

	/**
	 * @param cartId
	 * @param bookId
	 * @param quantity
	 */
	public CartedBook(long cartId, long bookId, int quantity) {
		this.cartId = cartId;
		this.bookId = bookId;
		this.quantity = quantity;
	}

	/**
	 * @return the cartId
	 */
	public long getCartId() {
		return cartId;
	}

	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the bookId
	 */
	public long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
