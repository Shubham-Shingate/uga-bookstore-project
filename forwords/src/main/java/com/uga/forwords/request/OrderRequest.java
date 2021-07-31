package com.uga.forwords.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.uga.forwords.model.BookEntry;

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
	private List<BookEntry> books;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getPromoId() {
		return promoId;
	}

	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}

	public Double getDiscountedCost() {
		return discountedCost;
	}

	public void setDiscountedCost(Double discountedCost) {
		this.discountedCost = discountedCost;
	}

	public List<BookEntry> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntry> books) {
		this.books = books;
	}

	public OrderRequest(String cardNumber, Long addressId, Double totalCost, String promoId, Double discountedCost,
			List<BookEntry> books) {
		this.cardNumber = cardNumber;
		this.addressId = addressId;
		this.totalCost = totalCost;
		this.promoId = promoId;
		this.discountedCost = discountedCost;
		this.books = books;
	}
	
	public OrderRequest() {
		
	}

	@Override
	public String toString() {
		return "OrderRequest [cardNumber=" + cardNumber + ", addressId=" + addressId + ", totalCost=" + totalCost
				+ ", promoId=" + promoId + ", discountedCost=" + discountedCost + ", books=" + books + "]";
	}

}
