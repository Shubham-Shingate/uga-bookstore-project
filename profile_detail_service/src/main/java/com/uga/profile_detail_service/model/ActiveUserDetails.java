package com.uga.profile_detail_service.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVE_USER_MASTER")
public class ActiveUserDetails {

	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "PHONE_NO")
	private String phoneNo;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Id
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	
	@Column(name = "CREATED_DATETIME")
	private Date createdDatetime;
	
	@Column(name = "ACCOUNT_STATUS")
	private String accountStatus;
	
	@Column(name = "SUBSCRIBED")
	private Long subscribed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Long getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Long subscribed) {
		this.subscribed = subscribed;
	}

	public ActiveUserDetails(Long id, String fullName, String phoneNo, String emailId, String password, String accountId,
			Date createdDatetime, String accountStatus, Long subscribed) {
		this.id = id;
		this.fullName = fullName;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.password = password;
		this.accountId = accountId;
		this.createdDatetime = createdDatetime;
		this.accountStatus = accountStatus;
		this.subscribed = subscribed;
	}
	
	public ActiveUserDetails() {
		
	}

	@Override
	public String toString() {
		return "ActiveUser [id=" + id + ", fullName=" + fullName + ", phoneNo=" + phoneNo + ", emailId=" + emailId
				+ ", password=" + password + ", accountId=" + accountId + ", createdDatetime=" + createdDatetime
				+ ", accountStatus=" + accountStatus + ", subscribed=" + subscribed + "]";
	}
	
	
	
		
	
}
