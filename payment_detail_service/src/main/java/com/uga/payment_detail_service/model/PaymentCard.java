package com.uga.payment_detail_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "PAYMENT_DETAILS_MASTER")
public class PaymentCard {
	
	@Id
	@Column(name="CARD_NUMBER")
	private String cardNumber;
	
	@Column(name="NAME_ON_CARD")
	private String name;
	
	@Column(name="CARD_TYPE")
	private String type;
	
	private String cvv;
	
	@Column(name="CARD_EXPIRY")
	private Timestamp cardExpiry;
	
	private String status;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;

	public PaymentCard(String cardNumber, String name, String type, String cvv, Timestamp cardExpiry, String status,
			String accountId) {
		this.cardNumber = cardNumber;
		this.name = name;
		this.type = type;
		this.cvv = cvv;
		this.cardExpiry = cardExpiry;
		this.status = status;
		this.accountId = accountId;
	}
	
	public PaymentCard() {
		
	}

	@Override
	public String toString() {
		return "PaymentCard [cardNumber=" + cardNumber + ", name=" + name + ", type=" + type + ", cvv=" + cvv
				+ ", cardExpiry=" + cardExpiry + ", status=" + status + ", accountId=" + accountId + "]";
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Timestamp getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(Timestamp cardExpiry) {
		this.cardExpiry = cardExpiry;
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
	
	
}
