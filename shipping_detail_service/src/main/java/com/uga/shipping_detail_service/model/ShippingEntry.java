package com.uga.shipping_detail_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHIPPING_ADDRESS_MASTER")
public class ShippingEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_id;
	
	private String street;
	
	private String city;
	
	private String state;
	
	@Column(name="ZIP_CODE")
	private String zip_code;
	
	private String status;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;
	
	
	public ShippingEntry(int address_id, String street, String city, String state, String zip_code, String status,
			String account_id) {
		this.address_id = address_id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.status = status;
		this.accountId = account_id;
	}
	
	public ShippingEntry(String street, String city, String state, String zip_code, String status,
			String account_id) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.status = status;
		this.accountId = account_id;
	}
	
	public ShippingEntry() {
		
	}
	
	@Override
	public String toString() {
		return "ShippingEntry [address_id=" + address_id + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zip_code=" + zip_code + ", status=" + status + ", accountId=" + accountId + "]";
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

	public String getAccount_id() {
		return accountId;
	}

	public void setAccount_id(String accountId) {
		this.accountId = accountId;
	}
	
	
}
