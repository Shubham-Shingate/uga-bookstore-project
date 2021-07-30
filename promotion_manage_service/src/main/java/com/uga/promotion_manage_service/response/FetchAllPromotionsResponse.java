package com.uga.promotion_manage_service.response;

import java.util.List;

import com.uga.promotion_manage_service.model.Promotion;

public class FetchAllPromotionsResponse {
	
	private String message;
	
	private Object apiError;
	
	private List<Promotion> promotions;

	
	
	/**
	 * 
	 */
	public FetchAllPromotionsResponse() {
	}

	/**
	 * @param message
	 * @param apiError
	 * @param promotions
	 */
	public FetchAllPromotionsResponse(String message, Object apiError, List<Promotion> promotions) {
		this.message = message;
		this.apiError = apiError;
		this.promotions = promotions;
	}

	@Override
	public String toString() {
		return "FetchAllPromotionsResponse [message=" + message + ", apiError=" + apiError + ", promotions="
				+ promotions + "]";
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
	 * @return the promotions
	 */
	public List<Promotion> getPromotions() {
		return promotions;
	}

	/**
	 * @param promotions the promotions to set
	 */
	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	
	
}
