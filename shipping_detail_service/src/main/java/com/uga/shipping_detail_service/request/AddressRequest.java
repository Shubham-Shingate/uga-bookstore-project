package com.uga.shipping_detail_service.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class AddressRequest {
	
	// if not provided -> new address will be generated
	// if provided -> will overwrite existing address
		
	private int address_id;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zip_code;
	
	private String status;

	public AddressRequest(int address_id, String street, String city, String state, String zip_code, String status) {
		this.address_id = address_id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.status = status;
	}
	
	public AddressRequest(String street, String city, String state, String zip_code, String status) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.status = status;
	}
	
	// Can pass request containing only address id (for deletion, fetching data)
	public AddressRequest(int address_id)
	{
		this.address_id = address_id;
	}
	
	public AddressRequest() {
		
	}

	@Override
	public String toString() {
		return "AddressRequest [address_id=" + address_id + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zip_code=" + zip_code + ", status=" + status + "]";
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
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
