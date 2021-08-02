package com.uga.forwords.model;

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
	private Long book_id;
	
	private String title;
	
	private String isbn;
	
	private String author;
	
	private String category;
	
	private String description;
	
	private Byte[] cover_picture;
	
	private Long publication_year;
	
	private String edition;

	private String publisher;
	
	private String book_status;
	
	private Long quantity_in_stock;
	
	private Long minimum_threshold;
	
	private Double price;
	
	private String sub_category;

	public Long getBook_id() {
		return book_id;
	}

	public void setBook_id(Long book_id) {
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

	public Byte[] getCover_picture() {
		return cover_picture;
	}

	public void setCover_picture(Byte[] cover_picture) {
		this.cover_picture = cover_picture;
	}

	public Long getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(Long publication_year) {
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

	public Long getQuantity_in_stock() {
		return quantity_in_stock;
	}

	public void setQuantity_in_stock(Long quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

	public Long getMinimum_threshold() {
		return minimum_threshold;
	}

	public void setMinimum_threshold(Long minimum_threshold) {
		this.minimum_threshold = minimum_threshold;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	
	public Book(Long book_id, String title, String isbn, String author, String category, String description,
			Byte[] cover_picture, Long publication_year, String edition, String publisher, String book_status,
			Long quantity_in_stock, Long minimum_threshold, Double price, String sub_category) {
		
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
		this.sub_category = sub_category;
	}

	/**
	 * @param title
	 * @param isbn
	 * @param author
	 * @param category
	 * @param description
	 * @param cover_picture
	 * @param publication_year
	 * @param edition
	 * @param publisher
	 * @param book_status
	 * @param quantity_in_stock
	 * @param minimum_threshold
	 * @param price
	 * @param sub_category
	 */
	public Book(String title, String isbn, String author, String category, String description, Byte[] cover_picture,
			Long publication_year, String edition, String publisher, String book_status, Long quantity_in_stock,
			Long minimum_threshold, Double price, String sub_category) {
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
		this.sub_category = sub_category;
	}

	public Book() {
		
	}

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", isbn=" + isbn + ", author=" + author + ", category="
				+ category + ", description=" + description + ", cover_picture=" + Arrays.toString(cover_picture)
				+ ", publication_year=" + publication_year + ", edition=" + edition + ", publisher=" + publisher
				+ ", book_status=" + book_status + ", quantity_in_stock=" + quantity_in_stock + ", minimum_threshold="
				+ minimum_threshold + ", price=" + price + ", sub_category=" + sub_category + "]";
	}

	
	
}
