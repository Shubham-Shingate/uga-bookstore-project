package com.uga.shipping_detail_service.response;

import java.util.List;

import com.uga.shipping_detail_service.model.*;

public class ShippingInfoResponse {
	private String message;
	
	private Object apiError;
	
	private List<ShippingEntry> addresses;

	public ShippingInfoResponse(String message, Object apiError, List<ShippingEntry> addresses) {
		this.message = message;
		this.apiError = apiError;
		this.addresses = addresses;
	}
	
	public ShippingInfoResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
		this.addresses = null;
	}

	@Override
	public String toString() {
		return "ShippingResponse [message=" + message + ", apiError=" + apiError + ", addresses=" + addresses + "]";
	}

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

	public List<ShippingEntry> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<ShippingEntry> addresses) {
		this.addresses = addresses;
	}
	
}
