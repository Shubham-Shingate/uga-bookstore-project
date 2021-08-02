package com.uga.forwords.request;

import javax.validation.constraints.NotBlank;
import com.uga.forwords.validator.FieldMatch;
import com.uga.forwords.validator.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "{password.mismatch}")
})
public class CreateAccountRequest {
	
	/**--- Personal Details ---*/
	
	@NotBlank(message = "{fullName.notblank}")
	private String fullName;
	
	@NotBlank(message = "{phoneNumber.notblank}")
	private String phoneNumber;
	
	@ValidEmail
	@NotBlank(message = "{emailId.notblank}")
	private String emailId;
	
	@NotBlank(message = "{password.notblank}")
	private String password;
	
	@NotBlank(message = "{matchingPassword.notblank}")
	private String matchingPassword;
	
	/**--- Address Details ---*/
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zipcode;
	
	/**--- Card Details ---*/
	
	private String cardNumber;
	
	private String nameOnCard;
	
	private String cardType;
	
	//@Pattern(regexp = "^[0-9]{3,4}$", message = "{cvv.pattern}")
	private String cvv;
	
	//@Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$", message = "{cardExpiry.pattern}")
	private String cardExpiry;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = cardExpiry;
	}

	public CreateAccountRequest(String fullName, String phoneNumber, String emailId, String password,
			String matchingPassword, String street, String city, String state, String zipcode, String cardNumber,
			String nameOnCard, String cardType, String cvv, String cardExpiry) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cardType = cardType;
		this.cvv = cvv;
		this.cardExpiry = cardExpiry;
	}
	
	public CreateAccountRequest() {
		
	}

	@Override
	public String toString() {
		return "CreateAccountRequest [fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", emailId=" + emailId
				+ ", password=" + password + ", matchingPassword=" + matchingPassword + ", street=" + street + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode + ", cardNumber=" + cardNumber + ", nameOnCard="
				+ nameOnCard + ", cardType=" + cardType + ", cvv=" + cvv + ", cardExpiry=" + cardExpiry + "]";
	}
	
}
