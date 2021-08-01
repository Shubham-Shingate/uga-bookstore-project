package com.uga.forwords.request;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class PromotionInfoRequest {

	private String promoId;
	
	private String startDatetime;
	
	private String endDatetime;

	private Double discountValue;

	/**
	 * 
	 */
	public PromotionInfoRequest() {
	}

	/**
	 * @param promoId
	 * @param startDatetime
	 * @param endDatetime
	 * @param discountValue
	 */
	public PromotionInfoRequest(@NotBlank(message = "promoId.notblank") String promoId,
			@NotBlank(message = "startDateTime.notblank") String startDatetime,
			@NotBlank(message = "endDateTime.notblank") String endDatetime,
			@NotBlank(message = "discountValue.notblank") Double discountValue) {
		this.promoId = promoId;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.discountValue = discountValue;
	}

	/**
	 * @return the promoId
	 */
	public String getPromoId() {
		return promoId;
	}

	/**
	 * @param promoId the promoId to set
	 */
	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	/**
	 * @return the startDatetime
	 */
	public String getStartDatetime() {
		return startDatetime;
	}

	/**
	 * @param startDatetime the startDatetime to set
	 */
	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}

	/**
	 * @return the endDatetime
	 */
	public String getEndDatetime() {
		return endDatetime;
	}

	/**
	 * @param endDatetime the endDatetime to set
	 */
	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}

	/**
	 * @return the discountValue
	 */
	public Double getDiscountValue() {
		return discountValue;
	}

	/**
	 * @param discountValue the discountValue to set
	 */
	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}
	
	public Timestamp getStartTimestamp() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			date = df.parse(startDatetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new Timestamp(date.getTime());
	}
	
	public Timestamp getEndTimestamp() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			date = df.parse(endDatetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return new Timestamp(date.getTime());
	}
	
	
}
