package com.uga.order_manage_service.response;

import java.util.List;

import com.uga.order_manage_service.model.Order;
import com.uga.order_manage_service.model.OrderBookMapping;

public class OrderResponse {
	
	private String message;
	
	private Object apiError;
	
	private Order order;
	
	private List<OrderBookMapping> booksInOrder;

	/**
	 * 
	 */
	public OrderResponse() {
	}

	/**
	 * @param message
	 * @param apiError
	 * @param order
	 * @param booksInOrder
	 */
	public OrderResponse(String message, Object apiError, Order order, List<OrderBookMapping> booksInOrder) {
		this.message = message;
		this.apiError = apiError;
		this.order = order;
		this.booksInOrder = booksInOrder;
	}

	/**
	 * @param message
	 * @param apiError
	 * @param order
	 */
	public OrderResponse(String message, Object apiError, Order order) {
		this.message = message;
		this.apiError = apiError;
		this.order = order;
	}

	/**
	 * @param message
	 * @param apiError
	 */
	public OrderResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the apiError
	 */
	public Object getApiError() {
		return apiError;
	}

	/**
	 * @param apiError the apiError to set
	 */
	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the booksInOrder
	 */
	public List<OrderBookMapping> getBooks() {
		return booksInOrder;
	}

	/**
	 * @param booksInOrder the booksInOrder to set
	 */
	public void setBooks(List<OrderBookMapping> booksInOrder) {
		this.booksInOrder = booksInOrder;
	}

	
}
