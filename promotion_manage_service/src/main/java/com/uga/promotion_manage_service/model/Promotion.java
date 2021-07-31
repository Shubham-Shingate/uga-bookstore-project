package com.uga.promotion_manage_service.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOTION_MASTER")
public class Promotion {
	
	@Id
	@Column(name = "PROMO_ID")
	private String promoId;
	
	@Column(name = "START_DATETIME")
	private Timestamp startDatetime;
	
	@Column(name = "END_DATETIME")
	private Timestamp endDatetime;
	
	@Column(name = "DISCOUNT_VALUE")
	private double discountValue;

	/**
	 * 
	 */
	public Promotion() {
	}

	/**
	 * @param promoId
	 * @param startDatetime
	 * @param endDatetime
	 * @param discountValue
	 */
	public Promotion(String promoId, Timestamp startDatetime, Timestamp endDatetime, double discountValue) {
		this.promoId = promoId;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.discountValue = discountValue;
	}

	@Override
	public String toString() {
		return "Promotion [promoId=" + promoId + ", startDatetime=" + startDatetime + ", endDatetime=" + endDatetime
				+ ", discountValue=" + discountValue + "]";
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
	public Timestamp getStartDatetime() {
		return startDatetime;
	}

	/**
	 * @param startDatetime the startDatetime to set
	 */
	public void setStartDatetime(Timestamp startDatetime) {
		this.startDatetime = startDatetime;
	}

	/**
	 * @return the endDatetime
	 */
	public Timestamp getEndDateTime() {
		return endDatetime;
	}

	/**
	 * @param endDatetime the endDatetime to set
	 */
	public void setEndDateTime(Timestamp endDatetime) {
		this.endDatetime = endDatetime;
	}

	/**
	 * @return the discountValue
	 */
	public double getDiscountValue() {
		return discountValue;
	}

	/**
	 * @param discountValue the discountValue to set
	 */
	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	
	
	
	
	
}