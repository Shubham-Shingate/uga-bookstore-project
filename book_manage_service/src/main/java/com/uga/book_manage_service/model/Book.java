package com.uga.book_manage_service.model;

import java.util.Arrays;

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
	private int publicationYear;
	
	private String edition;

	private String publisher;
	
	@Column(name = "BOOK_STATUS")
	private String bookStatus;
	
	@Column(name = "QUANTITY_IN_STOCK")
	private int quantity;
	
	@Column(name = "MINIMUM_THRESHOLD")
	private int minimumThreshold;
	
	private double price;

	public Book() {
	}

	public Book(String title, String isbn, String author, String category, String description, byte[] coverPicture,
			int publicationYear, String edition, String publisher, String bookStatus, int quantity,
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

	public Book(Long id, String title, String isbn, String author, String category, String description,
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

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", author=" + author + ", category="
				+ category + ", description=" + description + ", coverPicture=" + Arrays.toString(coverPicture)
				+ ", publicationYear=" + publicationYear + ", edition=" + edition + ", publisher=" + publisher
				+ ", bookStatus=" + bookStatus + ", quantity=" + quantity + ", minimumThreshold=" + minimumThreshold
				+ ", price=" + price + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
