package com.uga.book_manage_service.request;

public class ManageBookRequest {
	
	private int id;
	
	private String title;
	
	private String isbn;
	
	private String author;
	
	private String category;
	
	private String description;
	
	private byte[] coverPicture;

	private int publicationYear;
	
	private String edition;

	private String publisher;
	
	private String bookStatus;
	
	private int quantity;
	
	private int minimumThreshold;
	
	private double price;

	public ManageBookRequest() {
	}

	// For deleting a book by ID
	public ManageBookRequest(int id) {
		this.id = id;
	}
	
	

	public ManageBookRequest(String title, String isbn, String author, String category, String description,
			int publicationYear, String edition, String publisher, int quantity, int minimumThreshold, double price) {
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
			byte[] coverPicture, int publicationYear, String edition, String publisher, String bookStatus, int quantity,
			int minimumThreshold, double price) {
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
		public ManageBookRequest(String title, String isbn, String author, String category, String description, int publicationYear, String edition, String publisher, String bookStatus, int quantity,
				int minimumThreshold, double price) {
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
	public ManageBookRequest(int id, String title, String isbn, String author, String category, String description,
			byte[] coverPicture, int publicationYear, String edition, String publisher, String bookStatus, int quantity,
			int minimumThreshold, double price) {
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
		public ManageBookRequest(int id, String title, String isbn, String author, String category, String description, int publicationYear, String edition, String publisher, String bookStatus, int quantity,
				int minimumThreshold, double price) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public void setPublicationYear(int publicationYear) {
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMinimumThreshold() {
		return minimumThreshold;
	}

	public void setMinimumThreshold(int minimumThreshold) {
		this.minimumThreshold = minimumThreshold;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
