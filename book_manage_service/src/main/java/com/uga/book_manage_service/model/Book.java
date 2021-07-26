package com.uga.book_manage_service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_MASTER")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID")
	private Long id;
	
	private String title;
	
	private String isbn;
	
	private String author;
	
	private String category;
	
	private String description;
	
	@Column(name = "COVER_PICTURE")
	private byte[] coverPicture;
	
	@Column(name = "PUBLICATION_YEAR")
	private Integer publicationYear;
	
	private String edition;

	private String publisher;
	
	@Column(name = "BOOK_STATUS")
	private String bookStatus;
	
	@Column(name = "QUANTITY_IN_STOCK")
	private Long quantity;
	
	@Column(name = "MINIMUM_THRESHOLD")
	private Long minimumThreshold;
	
	private Double price;
	
	@Column(name = "SUB_CATEGORY")
	private String subCategory;
	
	/**
	 * 
	 */
	public Book() {
	}

	/**
	 * @param id
	 * @param title
	 * @param isbn
	 * @param author
	 * @param category
	 * @param description
	 * @param coverPicture
	 * @param publicationYear
	 * @param edition
	 * @param publisher
	 * @param bookStatus
	 * @param quantity
	 * @param minimumThreshold
	 * @param price
	 * @param subCategory
	 */
	public Book(Long id, String title, String isbn, String author, String category, String description,
			byte[] coverPicture, Integer publicationYear, String edition, String publisher, String bookStatus,
			Long quantity, Long minimumThreshold, Double price, String subCategory) {
		this.id = id;
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

	/**
	 * @param title
	 * @param isbn
	 * @param author
	 * @param category
	 * @param description
	 * @param coverPicture
	 * @param publicationYear
	 * @param edition
	 * @param publisher
	 * @param bookStatus
	 * @param quantity
	 * @param minimumThreshold
	 * @param price
	 * @param subCategory
	 */
	public Book(String title, String isbn, String author, String category, String description, byte[] coverPicture,
			Integer publicationYear, String edition, String publisher, String bookStatus, Long quantity,
			Long minimumThreshold, Double price, String subCategory) {
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the coverPicture
	 */
	public byte[] getCoverPicture() {
		return coverPicture;
	}

	/**
	 * @param coverPicture the coverPicture to set
	 */
	public void setCoverPicture(byte[] coverPicture) {
		this.coverPicture = coverPicture;
	}

	/**
	 * @return the publicationYear
	 */
	public Integer getPublicationYear() {
		return publicationYear;
	}

	/**
	 * @param publicationYear the publicationYear to set
	 */
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @param edition the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the bookStatus
	 */
	public String getBookStatus() {
		return bookStatus;
	}

	/**
	 * @param bookStatus the bookStatus to set
	 */
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the minimumThreshold
	 */
	public Long getMinimumThreshold() {
		return minimumThreshold;
	}

	/**
	 * @param minimumThreshold the minimumThreshold to set
	 */
	public void setMinimumThreshold(Long minimumThreshold) {
		this.minimumThreshold = minimumThreshold;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the subCategory
	 */
	public String getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
		

}
