package com.uga.forwords.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(CartBookId.class)
@Entity 
@Table(name = "CART_BOOK_MAPPING")
public class CartBookMapping {
	
	@Id
	@Column(name="CART_ID")
	private Long cartId;
	
	@Id
	@Column(name="BOOK_ID")
	private Long bookId;
	
	@Column(name="QUANTITY")
	private Integer quantity;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CartBookMapping(Long cartId, Long bookId, Integer quantity) {
		this.cartId = cartId;
		this.bookId = bookId;
		this.quantity = quantity;
	}

	public CartBookMapping() {
		
	}

	@Override
	public String toString() {
		return "CartBookMapping [cartId=" + cartId + ", bookId=" + bookId + ", quantity=" + quantity + "]";
	}
	
	
	

}
