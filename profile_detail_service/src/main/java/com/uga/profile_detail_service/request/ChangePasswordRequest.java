package com.uga.profile_detail_service.request;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequest {
	
	@NotBlank
	private String oldPassword;
	
	@NotBlank
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public ChangePasswordRequest(@NotBlank String oldPassword, @NotBlank String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	public ChangePasswordRequest() {
		
	}

	@Override
	public String toString() {
		return "ChangePasswordRequest [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}
	
	
}
