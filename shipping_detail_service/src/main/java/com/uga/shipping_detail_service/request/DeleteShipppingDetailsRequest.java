package com.uga.shipping_detail_service.request;

import javax.validation.constraints.NotNull;

public class DeleteShipppingDetailsRequest {

	@NotNull(message = "{addressId.notnull}")
	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public DeleteShipppingDetailsRequest(Long addressId) {
		this.addressId = addressId;
	}
	
	public DeleteShipppingDetailsRequest() {
		
	}

	@Override
	public String toString() {
		return "DeleteShipppingDetailsRequest [addressId=" + addressId + "]";
	}
	
	
	
}
