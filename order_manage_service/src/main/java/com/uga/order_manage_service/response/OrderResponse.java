package com.uga.order_manage_service.response;

import java.util.List;
import com.uga.order_manage_service.model.OrderWithDetails;

public class OrderResponse {

	private String message;
	
	private Object apiError;
	
	private List<OrderWithDetails> orders;

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

	public List<OrderWithDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderWithDetails> orders) {
		this.orders = orders;
	}

	public OrderResponse(String message, Object apiError, List<OrderWithDetails> orders) {
		this.message = message;
		this.apiError = apiError;
		this.orders = orders;
	}
	
	public OrderResponse() {
	
	}

	@Override
	public String toString() {
		return "OrderResponse [message=" + message + ", apiError=" + apiError + ", orders=" + orders + "]";
	}

	
}
