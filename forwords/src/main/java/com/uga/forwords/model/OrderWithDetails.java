package com.uga.forwords.model;

import java.util.Collection;
import java.util.Date;

public class OrderWithDetails {

	private String orderId;
	
	private String accountId;
	
	private String cardNumber;
	
	private Long addressId;
	
	private Double totalCost;
	
	private String promoId;
	
	private Double discountedCost;
	
	private Date orderDate;	
	
	private Collection<Book> books;
	
	private ShippingEntry shippingDetails;

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

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	public ShippingEntry getShippingDetails() {
		return shippingDetails;
	}

	public void setShippingDetails(ShippingEntry shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	public OrderWithDetails(String orderId, String accountId, String cardNumber, Long addressId, Double totalCost,
			String promoId, Double discountedCost, Date orderDate, Collection<Book> books,
			ShippingEntry shippingDetails) {
		this.orderId = orderId;
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = promoId;
		this.discountedCost = discountedCost;
		this.orderDate = orderDate;
		this.books = books;
		this.shippingDetails = shippingDetails;
	}
	
	public OrderWithDetails() {
		
	}

	@Override
	public String toString() {
		return "OrderWithDetails [orderId=" + orderId + ", accountId=" + accountId + ", cardNumber=" + cardNumber
				+ ", addressId=" + addressId + ", totalCost=" + totalCost + ", promoId=" + promoId + ", discountedCost="
				+ discountedCost + ", orderDate=" + orderDate + ", books=" + books + ", shippingDetails="
				+ shippingDetails + "]";
	}

	
	
}
