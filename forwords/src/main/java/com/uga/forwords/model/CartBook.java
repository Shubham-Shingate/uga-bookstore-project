package com.uga.forwords.model;

import java.util.Arrays;

public class CartBook {
	
	private Long bookId;
	
	private String title;
	
	private String isbn;
	
	private String author;
	
	private String category;
	
	private String description;
	
	private Byte[] coverPicture;
	
	private Long publicationYear;
	
	private String edition;

	private String publisher;
	
	private String bookStatus;
	
	private Long quantity;
	
	private Long minimumThreshold;
	
	private Double price;
	
	private String subCategory;
	
	private Integer quantityInCart;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(Byte[] coverPicture) {
		this.coverPicture = coverPicture;
	}

	public Long getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Long publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getMinimumThreshold() {
		return minimumThreshold;
	}

	public void setMinimumThreshold(Long minimumThreshold) {
		this.minimumThreshold = minimumThreshold;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	public Integer getQuantityInCart() {
		return quantityInCart;
	}

	public void setQuantityInCart(Integer quantityInCart) {
		this.quantityInCart = quantityInCart;
	}

	public CartBook(Long bookId, String title, String isbn, String author, String category, String description,
			Byte[] coverPicture, Long publicationYear, String edition, String publisher, String bookStatus,
			Long quantity, Long minimumThreshold, Double price, String subCategory) {
		this.bookId = bookId;
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.description = description;
		this.coverPicture = coverPicture;
		this.publicationYear = publicationYear;
		this.edition = edition;
		this.publisher = publisher;
		this.bookStatus = bookStatus;
		this.quantity = quantity;
		this.minimumThreshold = minimumThreshold;
		this.price = price;
		this.subCategory = subCategory;
	}
	
	public CartBook(String title, String isbn, String author, String category, String description,
			Byte[] coverPicture, Long publicationYear, String edition, String publisher, String bookStatus,
			Long quantity, Long minimumThreshold, Double price, String subCategory) {
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.description = description;
		this.coverPicture = coverPicture;
		this.publicationYear = publicationYear;
		this.edition = edition;
		this.publisher = publisher;
		this.bookStatus = bookStatus;
		this.quantity = quantity;
		this.minimumThreshold = minimumThreshold;
		this.price = price;
		this.subCategory = subCategory;
	}

	
	
	public CartBook(Long bookId, String title, String isbn, String author, String category, String description,
			Byte[] coverPicture, Long publicationYear, String edition, String publisher, String bookStatus,
			Long quantity, Long minimumThreshold, Double price, String subCategory, Integer quantityInCart) {
		this.bookId = bookId;
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.description = description;
		this.coverPicture = coverPicture;
		this.publicationYear = publicationYear;
		this.edition = edition;
		this.publisher = publisher;
		this.bookStatus = bookStatus;
		this.quantity = quantity;
		this.minimumThreshold = minimumThreshold;
		this.price = price;
		this.subCategory = subCategory;
		this.quantityInCart = quantityInCart;
	}

	public CartBook() {
		
	}

	@Override
	public String toString() {
		return "CartBook [bookId=" + bookId + ", title=" + title + ", isbn=" + isbn + ", author=" + author
				+ ", category=" + category + ", description=" + description + ", coverPicture="
				+ Arrays.toString(coverPicture) + ", publicationYear=" + publicationYear + ", edition=" + edition
				+ ", publisher=" + publisher + ", bookStatus=" + bookStatus + ", quantity=" + quantity
				+ ", minimumThreshold=" + minimumThreshold + ", price=" + price + ", subCategory=" + subCategory + "]";
	}

	

	
	
	
}
