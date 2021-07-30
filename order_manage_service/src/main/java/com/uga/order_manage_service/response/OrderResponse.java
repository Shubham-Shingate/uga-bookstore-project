package com.uga.order_manage_service.response;

import java.util.List;

import com.uga.order_manage_service.model.Order;

public class OrderResponse {

	private String message;
	
	private Object apiError;
	
	private List<Order> orders;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getApiError() {
		return apiError;
	}

	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public OrderResponse(String message, Object apiError, List<Order> orders) {
		this.message = message;
		this.apiError = apiError;
		this.orders = orders;
	}
	
	public OrderResponse() {
		
	}

	@Override
	public String toString() {
		return "OrderListResponse [message=" + message + ", apiError=" + apiError + ", orders=" + orders + "]";
	}
	
	
	
}
