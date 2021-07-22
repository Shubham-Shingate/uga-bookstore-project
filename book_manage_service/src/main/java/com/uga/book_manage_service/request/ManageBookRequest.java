package com.uga.book_manage_service.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ManageBookRequest {
	
	private long id;
	
	@NotBlank(message="{title.notblank}")
	private String title;
	
	@NotBlank(message="{isbn.notblank}")
	private String isbn;
	
	@NotBlank(message="{author.notblank}")
	private String author;
	
	@NotBlank(message="{category.notblank}")
	private String category;
	

	private String description;
		
	private byte[] coverPicture;
    
	@NotNull(message="{publicationYear.notblank}")
    private Integer publicationYear;
	

	private String edition;
   
	@NotBlank(message="{publisher.notblank}")
    private String publisher;
	
    
	private String bookStatus;
	
	@NotNull(message="{quantity.notblank}")
	private Integer quantity;
	
	@NotNull(message="{minimumThreshold.notblank}")
	private Integer minimumThreshold;
	
	@NotNull(message="{price.notblank}")
	private Double price;

	public ManageBookRequest() {
	}

	// For deleting a book by ID
	public ManageBookRequest(long id) {
		this.id = id;
	}
	
	

	public ManageBookRequest(String title, String isbn, String author, String category, String description,
			Integer publicationYear, String edition, String publisher, Integer quantity, Integer minimumThreshold, Double price) {
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.description = description;
		this.publicationYear = publicationYear;
		this.edition = edition;
		this.publisher = publisher;
		this.quantity = quantity;
		this.minimumThreshold = minimumThreshold;
		this.price = price;
	}

	// For adding a new book, ID auto-generated
	public ManageBookRequest(String title, String isbn, String author, String category, String description,
			byte[] coverPicture, Integer publicationYear, String edition, String publisher, String bookStatus, Integer quantity,
			Integer minimumThreshold, Double price) {
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
	}
	
	// No id, no cover picture
		public ManageBookRequest(String title, String isbn, String author, String category, String description, Integer publicationYear, String edition, String publisher, String bookStatus, Integer quantity,
				Integer minimumThreshold, Double price) {
			this.title = title;
			this.isbn = isbn;
			this.author = author;
			this.category = category;
			this.description = description;
			this.publicationYear = publicationYear;
			this.edition = edition;
			this.publisher = publisher;
			this.bookStatus = bookStatus;
			this.quantity = quantity;
			this.minimumThreshold = minimumThreshold;
			this.price = price;
		}

	// For updating an existing book
	public ManageBookRequest(long id, String title, String isbn, String author, String category, String description,
			byte[] coverPicture, Integer publicationYear, String edition, String publisher, String bookStatus, Integer quantity,
			Integer minimumThreshold, Double price) {
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
	}
	
	// no cover picture
		public ManageBookRequest(long id, String title, String isbn, String author, String category, String description, Integer publicationYear, String edition, String publisher, String bookStatus, Integer quantity,
				Integer minimumThreshold, Double price) {
			this.id = id;
			this.title = title;
			this.isbn = isbn;
			this.author = author;
			this.category = category;
			this.description = description;
			this.publicationYear = publicationYear;
			this.edition = edition;
			this.publisher = publisher;
			this.bookStatus = bookStatus;
			this.quantity = quantity;
			this.minimumThreshold = minimumThreshold;
			this.price = price;
		}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public byte[] getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(byte[] coverPicture) {
		this.coverPicture = coverPicture;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public int getMinimumThreshold() {
		return minimumThreshold;
	}

	public void setMinimumThreshold(Integer minimumThreshold) {
		this.minimumThreshold = minimumThreshold;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}
