package com.uga.forwords.model;

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
	@Column(name="ADDRESS_ID")
	private Long addressId;
	
	private String street;
	
	private String city;
	
	private String state;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	private String status;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public ShippingEntry(Long addressId, String street, String city, String state, String zipCode, String status,
			String accountId) {
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.status = status;
		this.accountId = accountId;
	}
	
	public ShippingEntry() {
	
	}

	@Override
	public String toString() {
		return "ShippingEntry [addressId=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + ", status=" + status + ", accountId=" + accountId + "]";
	}
	
}
