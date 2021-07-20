package com.uga.payment_detail_service.request;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CardRequest {
	
	@NotBlank(message = "cardNumber.notblank")
	private String cardNumber;
	
	@NotBlank(message = "nameOnCard.notblank")
	private String nameOnCard;
	
	@NotBlank(message = "cardType.notblank")
	private String cardType;
	
	@NotBlank(message = "cvv.notblank")
	private String cvv;
	
	@NotBlank(message = "cardExpiry.notblank")
	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$", message = "{cardExpiry.pattern}")
    private String cardExpiry;
	
	private String status;

	public CardRequest(String cardNumber, String nameOnCard, String cardType, String cvv, String cardExpiry,
			String status) {
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cardType = cardType;
		this.cvv = cvv;
		this.cardExpiry = cardExpiry;
		this.status = status;
	}	
	
	public CardRequest(String cardNumber, String nameOnCard, String cardExpiry) {
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cardExpiry = cardExpiry;
	}
	
	public CardRequest() {
		
	}

	@Override
	public String toString() {
		return "CardRequest [cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", cardType=" + cardType
				+ ", cvv=" + cvv + ", cardExpiry=" + cardExpiry + ", status=" + status + "]";
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Timestamp getExpiryTimestamp()
	{
		Date date = new Date();
		if(cardExpiry.length() == 5) {
			DateFormat df = new SimpleDateFormat("MM/yy");
			try {
				date = df.parse(cardExpiry);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			DateFormat df = new SimpleDateFormat("MM/yyyy");
			try {
				date = df.parse(cardExpiry);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new Timestamp(date.getTime());
	}
	
	
	

}
