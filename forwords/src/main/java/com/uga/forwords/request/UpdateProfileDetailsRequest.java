package com.uga.forwords.request;

public class UpdateProfileDetailsRequest {
	
	private String fullName;
	
	private String phoneNo;

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

	public UpdateProfileDetailsRequest(String fullName, String phoneNo) {
		this.fullName = fullName;
		this.phoneNo = phoneNo;
	}
	
	public UpdateProfileDetailsRequest() {
		
	}

	@Override
	public String toString() {
		return "UpdateProfileDetailsRequest [fullName=" + fullName + ", phoneNo=" + phoneNo + "]";
	}
	
	
	
}
