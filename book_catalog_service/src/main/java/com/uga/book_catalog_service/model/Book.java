package com.uga.book_catalog_service.model;

import java.util.Arrays;
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
	private int book_id;
	
	private String title;
	
	private String isbn;
	
	private String author;
	
	private String category;
	
	private String description;
	
	private byte[] cover_picture;
	
	private int publication_year;
	
	private String edition;

	private String publisher;
	
	private String book_status;
	
	private int quantity_in_stock;
	
	private int minimum_threshold;
	
	private double price;

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
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

	public byte[] getCover_picture() {
		return cover_picture;
	}

	public void setCover_picture(byte[] cover_picture) {
		this.cover_picture = cover_picture;
	}

	public int getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
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

	public String getBook_status() {
		return book_status;
	}

	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}

	public int getQuantity_in_stock() {
		return quantity_in_stock;
	}

	public void setQuantity_in_stock(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

	public int getMinimum_threshold() {
		return minimum_threshold;
	}

	public void setMinimum_threshold(int minimum_threshold) {
		this.minimum_threshold = minimum_threshold;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Book(int book_id, String title, String isbn, String author, String category, String description,
			byte[] cover_picture, int publication_year, String edition, String publisher, String book_status,
			int quantity_in_stock, int minimum_threshold, double price) {
		this.book_id = book_id;
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.category = category;
		this.description = description;
		this.cover_picture = cover_picture;
		this.publication_year = publication_year;
		this.edition = edition;
		this.publisher = publisher;
		this.book_status = book_status;
		this.quantity_in_stock = quantity_in_stock;
		this.minimum_threshold = minimum_threshold;
		this.price = price;
	}
	
	public Book() {
		
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", isbn=" + isbn + ", author=" + author + ", category="
				+ category + ", description=" + description + ", cover_picture=" + Arrays.toString(cover_picture)
				+ ", publication_year=" + publication_year + ", edition=" + edition + ", publisher=" + publisher
				+ ", book_status=" + book_status + ", quantity_in_stock=" + quantity_in_stock + ", minimum_threshold="
				+ minimum_threshold + ", price=" + price + "]";
	}	
	

}
