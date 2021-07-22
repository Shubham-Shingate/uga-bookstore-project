package com.uga.validate_token_service.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VERIFICATION_TOKEN")
public class ValidationToken {
	
	@Id
	@Column(name="TOKEN_ID")
	private int tokenId;
	
	@Column(name="VERIFICATION_TOKEN")
	private String validationToken;
	
	@Column(name="CREATED_DATETIME")
	private Timestamp createdDateTime;
	
	@Column(name="EXPIRY_DATETIME")
	private Timestamp expiryDateTime;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;
	
	@Column(name="STATUS")
	private String status;

	/**
	 * 
	 */
	public ValidationToken() {
	}

	/**
	 * @param tokenId
	 * @param validationToken
	 * @param createdDateTime
	 * @param expiryDateTime
	 * @param accountId
	 * @param status
	 */
	public ValidationToken(int tokenId, String validationToken, Timestamp createdDateTime, Timestamp expiryDateTime,
			String accountId, String status) {
		this.tokenId = tokenId;
		this.validationToken = validationToken;
		this.createdDateTime = createdDateTime;
		this.expiryDateTime = expiryDateTime;
		this.accountId = accountId;
		this.status = status;
	}

	/**
	 * @return the tokenId
	 */
	public int getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the validationToken
	 */
	public String getValidationToken() {
		return validationToken;
	}

	/**
	 * @param validationToken the validationToken to set
	 */
	public void setValidationToken(String validationToken) {
		this.validationToken = validationToken;
	}

	/**
	 * @return the createdDateTime
	 */
	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	/**
	 * @return the expiryDateTime
	 */
	public Timestamp getExpiryDateTime() {
		return expiryDateTime;
	}

	/**
	 * @param expiryDateTime the expiryDateTime to set
	 */
	public void setExpiryDateTime(Timestamp expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
