package com.uga.forwords.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CART_MASTER")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ID")
	private Long cartId;
	
	@Column(name = "ACCOUNT_ID")
	private String accountId;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Cart(Long cartId, String accountId) {
		this.cartId = cartId;
		this.accountId = accountId;
	}
	
	public Cart(String accountId) {
		this.accountId = accountId;
	}
	
	public Cart() {
	
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", accountId=" + accountId + "]";
	}
	
	
}
