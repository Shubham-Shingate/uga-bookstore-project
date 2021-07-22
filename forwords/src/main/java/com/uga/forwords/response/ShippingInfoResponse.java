package com.uga.forwords.response;

import java.util.List;
import com.uga.forwords.model.ShippingEntry;

public class ShippingInfoResponse {
	
	private String message;
	
	private Object apiError;
	
	private List<ShippingEntry> addresses;

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

	public ShippingInfoResponse(String message, Object apiError, List<ShippingEntry> addresses) {
		this.message = message;
		this.apiError = apiError;
		this.addresses = addresses;
	}
	
	public ShippingInfoResponse() {
		
	}

	@Override
	public String toString() {
		return "ShippingInfoResponse [message=" + message + ", apiError=" + apiError + ", addresses=" + addresses + "]";
	}	
	
}
