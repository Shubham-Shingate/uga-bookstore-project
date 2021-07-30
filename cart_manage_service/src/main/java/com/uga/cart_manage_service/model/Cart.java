package com.uga.cart_manage_service.model;

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
	@Column(name="CART_ID")
	private Long cartId;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;

	/**
	 * 
	 */
	public Cart() {
	}

	/**
	 * @param cartId
	 * @param accountId
	 */
	public Cart(Long cartId, String accountId) {
		this.cartId = cartId;
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "CartMatch [cartId=" + cartId + ", accountId=" + accountId + "]";
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
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	

}
