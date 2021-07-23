package com.uga.forwords.request;


public class DeleteShipppingDetailsRequest {

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
