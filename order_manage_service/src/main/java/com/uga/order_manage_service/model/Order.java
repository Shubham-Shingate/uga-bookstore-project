package com.uga.order_manage_service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_MASTER")
public class Order {
	
	@Id
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "ADDRESS_ID")
	private Long addressId;
	
	@Column(name = "TOTAL_COST")
	private Double totalCost;
	
	@Column(name = "PROMO_ID")
	private String promoId;
	
	@Column(name = "DISCOUNTED_COST")
	private Double discountedCost;
	
	@Column(name = "CREATED_DATETIME")
	private Date orderDate;

	/**
	 * 
	 */
	public Order() {
	}

	/**
	 * @param accountId
	 * @param cardNumber
	 * @param addressId
	 * @param totalCost
	 * @param promoId
	 * @param discountedCost
	 */
	public Order(String accountId, String cardNumber, Long addressId, Double totalCost, String promoId,
			Double discountedCost) {
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = promoId;
		this.discountedCost = discountedCost;
	}

	/**
	 * @param accountId
	 * @param cardNumber
	 * @param addressId
	 * @param totalCost
	 * @param promoId
	 * @param discountedCost
	 * @param orderDate
	 */
	public Order(String accountId, String cardNumber, Long addressId, Double totalCost, String promoId,
			Double discountedCost, Date orderDate) {
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = promoId;
		this.discountedCost = discountedCost;
		this.orderDate = orderDate;
	}

	/**
	 * @param orderId
	 * @param accountId
	 * @param cardNumber
	 * @param addressId
	 * @param totalCost
	 * @param promoId
	 * @param discountedCost
	 * @param orderDate
	 */
	public Order(String orderId, String accountId, String cardNumber, Long addressId, Double totalCost, String promoId,
			Double discountedCost, Date orderDate) {
		this.orderId = orderId;
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = promoId;
		this.discountedCost = discountedCost;
		this.orderDate = orderDate;
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

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the addressId
	 */
	public Long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the totalCost
	 */
	public Double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the promoId
	 */
	public String getPromoId() {
		return promoId;
	}

	/**
	 * @param promoId the promoId to set
	 */
	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	/**
	 * @return the discountedCost
	 */
	public Double getDiscountedCost() {
		return discountedCost;
	}

	/**
	 * @param discountedCost the discountedCost to set
	 */
	public void setDiscountedCost(Double discountedCost) {
		this.discountedCost = discountedCost;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}
