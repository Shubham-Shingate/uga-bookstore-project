package com.uga.order_manage_service.response;

import java.util.List;

import com.uga.order_manage_service.model.Order;

public class OrderListResponse {

	private String message;
	
	private Object apiError;
	
	private List<Order> orders;

	/**
	 * 
	 */
	public OrderListResponse() {
	}

	/**
	 * @param message
	 * @param apiError
	 */
	public OrderListResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
	}

	/**
	 * @param message
	 * @param apiError
	 * @param orders
	 */
	public OrderListResponse(String message, Object apiError, List<Order> orders) {
		this.message = message;
		this.apiError = apiError;
		this.orders = orders;
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
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
