package com.uga.order_manage_service.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.uga.order_manage_service.model.BookEntry;

public class OrderRequest {
	
	@NotBlank(message = "{cardNumber.notBlank}")
	private String cardNumber;
	
	@NotNull(message = "{addressId.notNull}")
	@Min(value = 1)
	private Long addressId;
	
	@NotNull(message = "{totalCost.notNull}")	
	@Min(value = 0)
	private Double totalCost;
		
	private String promoId;
	
	@NotNull(message = "{discountedCost.notNull}")
	@Min(value = 0)
	private Double discountedCost;
	
	@NotNull(message = "{books.notBlank}")
	private BookEntry[] books;
	// First index is the book ID
	// Second index is the quantity

	/**
	 * @param cardNumber
	 * @param addressId
	 * @param totalCost
	 * @param discountedCost
	 * @param books
	 */
	public OrderRequest(@NotBlank(message = "{cardNumber.notBlank}") String cardNumber,
			@NotNull(message = "{addressId.notNull}") Long addressId,
			@NotNull(message = "{totalCost.notNull}") Double totalCost,
			@NotNull(message = "{discountedCost.notNull}") Double discountedCost,
			@NotNull(message = "{books.notBlank}") BookEntry[] books) {
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = null;
		this.discountedCost = discountedCost;
		this.books = books;
	}
	
	/**
	 * 
	 */
	public OrderRequest() {
	}

	/**
	 * @param cardNumber
	 * @param addressId
	 * @param totalCost
	 * @param promoId
	 * @param discountedCost
	 * @param books
	 */
	public OrderRequest(@NotBlank(message = "{cardNumber.notBlank}") String cardNumber,
			@NotNull(message = "{addressId.notNull}") Long addressId,
			@NotNull(message = "{totalCost.notNull}") Double totalCost,
			@NotNull(message = "{promoId.notBlank}") String promoId,
			@NotNull(message = "{discountedCost.notNull}") Double discountedCost,
			@NotNull(message = "{books.notBlank}") BookEntry[] books) {
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = promoId;
		this.discountedCost = discountedCost;
		this.books = books;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the addressId
	 */
	public long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
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
	 * @return the discountedCost
	 */
	public double getDiscountedCost() {
		return discountedCost;
	}

	/**
	 * @param discountedCost the discountedCost to set
	 */
	public void setDiscountedCost(Double discountedCost) {
		this.discountedCost = discountedCost;
	}

	/**
	 * @return the books
	 */
	public BookEntry[] getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(BookEntry[] books) {
		this.books = books;
	}

	
	
	

	
	
	

}
