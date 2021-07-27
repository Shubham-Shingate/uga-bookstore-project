package com.uga.promotion_manage_service.response;

import com.uga.promotion_manage_service.model.Promotion;

public class PromotionInfoResponse {
	
	private String message;
	
	private Object apiError;
	
	private Promotion promotion;

	/**
	 * @param message
	 * @param apiError
	 */
	public PromotionInfoResponse(String message, Object apiError) {
		this.message = message;
		this.apiError = apiError;
	}

	/**
	 * @param message
	 * @param apiError
	 * @param promotion
	 */
	public PromotionInfoResponse(String message, Object apiError, Promotion promotion) {
		this.message = message;
		this.apiError = apiError;
		this.promotion = promotion;
	}

	@Override
	public String toString() {
		return "PromotionInfoResponse [message=" + message + ", apiError=" + apiError + ", promotion=" + promotion
				+ "]";
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the apiError
	 */
	public Object getApiError() {
		return apiError;
	}

	/**
	 * @param apiError the apiError to set
	 */
	public void setApiError(Object apiError) {
		this.apiError = apiError;
	}

	/**
	 * @return the promotion
	 */
	public Promotion getPromotion() {
		return promotion;
	}

	/**
	 * @param promotion the promotion to set
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	

}
