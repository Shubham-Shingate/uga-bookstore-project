package com.uga.forwords.request;

import javax.validation.constraints.NotBlank;

public class ResetPasswordRequest {
	
	@NotBlank
	private String newPassword;
	
	@NotBlank
	private String accountId;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public ResetPasswordRequest(@NotBlank String newPassword, @NotBlank String accountId) {
		this.newPassword = newPassword;
		this.accountId = accountId;
	}
	
	public ResetPasswordRequest() {
		
	}

	@Override
	public String toString() {
		return "ResetPasswordRequest [newPassword=" + newPassword + ", accountId=" + accountId + "]";
	}
	
	
}
