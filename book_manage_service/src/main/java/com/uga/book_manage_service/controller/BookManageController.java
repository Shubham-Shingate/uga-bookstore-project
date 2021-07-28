package com.uga.book_manage_service.controller;

import com.uga.book_manage_service.service.BookRepository;

import java.io.IOException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uga.book_manage_service.exception.BookNotFoundException;
import com.uga.book_manage_service.exception.ImageException;
import com.uga.book_manage_service.exception.InvalidFieldException;
import com.uga.book_manage_service.model.Book;
import com.uga.book_manage_service.response.BookResponse;

@RestController
public class BookManageController {

	@Autowired
	private BookRepository bookRepository;	
	
	/* updateBook with Image */
	@PostMapping(value = "/updateBook", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.IMAGE_PNG_VALUE} )
	public ResponseEntity<BookResponse> addBook(
			@RequestParam Long id,
			@RequestParam String title,
			@RequestParam String isbn,
			@RequestParam String author,
			@RequestParam String category,
			@RequestParam String description,
			@RequestPart(value="coverPicture") MultipartFile coverPicture,
			@RequestParam Integer publicationYear,
			@RequestParam String edition,
			@RequestParam String publisher,
			@RequestParam Long quantity,
			@RequestParam Long minThreshold,
			@RequestParam Double price,
			@RequestParam String subCategory
			) throws IOException {
		
		/* Cannot get the above validations to work */
		// Check validity of parameters
		if(title == null || isbn == null || author == null || category == null || publicationYear == null || description == null || 
				coverPicture == null || publisher == null || quantity == null || minThreshold == null || price == null || subCategory == null)
			throw new InvalidFieldException("No values can be null. Some String values can be empty, but never null.");
		
		if(title.isEmpty() || isbn.isEmpty() || author.isEmpty() || category.isEmpty() || publisher.isEmpty() || price.toString().isEmpty() 
				|| quantity.toString().isEmpty() || minThreshold.toString().isEmpty() || publicationYear.toString().isEmpty() || coverPicture.isEmpty())
			throw new InvalidFieldException("One or more mandatory fields is empty. Review your submission and make sure no required values are left blank.");
		
		// Calculate status
		String book_status;
		if(quantity > 0)
			book_status = "AVAILABLE";
		else if(quantity < 0) {
			quantity = 0L;
			book_status = "UNAVAILABLE";
		}
		else
			book_status = "UNAVAILABLE";
		
		// Convert image to byte[]
		byte[] img;
		try {
			img = coverPicture.getBytes();
		}
		catch(IOException e) {
			throw new ImageException("Problem encountered while converting image to bit[] for database storage");
		}
		
		Book newBook = new Book(
				id,
				title, 
				isbn, 
				author, 
				category, 
				description, 
				img,
				publicationYear, 
				edition, 
				publisher,
				book_status, 
				quantity, 
				minThreshold, 
				price,
				subCategory
				);
		
		// Add to DB
		bookRepository.save(newBook);
		
		// Return response
		BookResponse bookResponse = new BookResponse("Success", null);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	// View book by id
	@GetMapping("/viewBook/{id}")
	public ResponseEntity<BookResponse> viewBook(@PathVariable long id) {
		Book book = bookRepository.findById(id);
		
		if(book == null)
			throw new BookNotFoundException("This book isn't in our system");
		
		BookResponse response = new BookResponse("Success", null, bookRepository.findById(id));
		return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
	}	
	 
	// Delete Book endpoint
	@GetMapping("/deleteBook/{id}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable long id) {
		Book book = bookRepository.findById(id);
		
		if(book == null)
			throw new BookNotFoundException("This book isn't in our system");
				
		bookRepository.delete(book);
		BookResponse response = new BookResponse("Success", null);
		return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
	}
}
