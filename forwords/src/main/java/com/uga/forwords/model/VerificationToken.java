package com.uga.forwords.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VERIFICATION_TOKEN")
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOKEN_ID")
	private Long tokenID;
	
	@Column(name = "VERIFICATION_TOKEN")
	private String verificationToken;
	
	@Column(name = "CREATED_DATETIME")
	private Date createdDatetime;
	
	@Column(name = "EXPIRY_DATETIME")
	private Date expiryDatetime;
	
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	
	@Column(name = "STATUS")
	private String status;

	public Long getTokenID() {
		return tokenID;
	}

	public void setTokenID(Long tokenID) {
		this.tokenID = tokenID;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Date getExpiryDatetime() {
		return expiryDatetime;
	}

	public void setExpiryDatetime(Date expiryDatetime) {
		this.expiryDatetime = expiryDatetime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VerificationToken(Long tokenID, String verificationToken, Date createdDatetime, Date expiryDatetime,
			String accountId, String status) {
		this.tokenID = tokenID;
		this.verificationToken = verificationToken;
		this.createdDatetime = createdDatetime;
		this.expiryDatetime = expiryDatetime;
		this.accountId = accountId;
		this.status = status;
	}
	
	public VerificationToken() {
		
	}

	@Override
	public String toString() {
		return "VerificationToken [tokenID=" + tokenID + ", verificationToken=" + verificationToken
				+ ", createdDatetime=" + createdDatetime + ", expiryDatetime=" + expiryDatetime + ", accountId="
				+ accountId + ", status=" + status + "]";
	}
	
}
