package com.uga.shipping_detail_service.request;

import javax.validation.constraints.NotBlank;

public class ShippingInfoRequest {
			
	private Long address_id;
	
	@NotBlank(message = "{street.notblank}")
	private String street;
	
	@NotBlank(message = "{city.notblank}")
	private String city;
	
	@NotBlank(message = "{state.notblank}")
	private String state;
	
	@NotBlank(message = "{zip_code.notblank}")
	private String zip_code;
	
	private String status;

	public ShippingInfoRequest(Long address_id, String street, String city, String state, String zip_code, String status) {
		this.address_id = address_id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.status = status;
	}
	
	public ShippingInfoRequest(String street, String city, String state, String zip_code, String status) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.status = status;
	}
	
	// Can pass request containing only address id (for deletion, fetching data)
	public ShippingInfoRequest(Long address_id)
	{
		this.address_id = address_id;
	}
	
	public ShippingInfoRequest() {
		
	}

	@Override
	public String toString() {
		return "AddressRequest [address_id=" + address_id + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zip_code=" + zip_code + ", status=" + status + "]";
	}

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
