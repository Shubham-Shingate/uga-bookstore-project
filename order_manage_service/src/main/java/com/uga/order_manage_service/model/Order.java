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
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getPromoId() {
		return promoId;
	}

	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	public Double getDiscountedCost() {
		return discountedCost;
	}

	public void setDiscountedCost(Double discountedCost) {
		this.discountedCost = discountedCost;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Order() {
		
	}

	public Order(String accountId, String cardNumber, Long addressId, Double totalCost, String promoId,
			Double discountedCost) {
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = promoId;
		this.discountedCost = discountedCost;
	}

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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", accountId=" + accountId + ", cardNumber=" + cardNumber + ", addressId="
				+ addressId + ", totalCost=" + totalCost + ", promoId=" + promoId + ", discountedCost=" + discountedCost
				+ ", orderDate=" + orderDate + "]";
	}
	
}
