package com.uga.book_manage_service.controller;

import com.uga.book_manage_service.service.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.uga.book_manage_service.model.Book;
import com.uga.book_manage_service.request.ManageBookRequest;
import com.uga.book_manage_service.response.BookResponse;

@RestController
public class BookManagementController {

	@Autowired
	private BookRepository bookRepository;
	
	// CREATE or UPDATE 
	// New book created if no ID included in request
	// Existing book updated if ID included
	@PostMapping("/updateBook")
	public ResponseEntity<BookResponse> addBook(@RequestBody @Validated ManageBookRequest request) {
		// Execute operation
		String book_status;
		if(request.getQuantity() > 0)
			book_status = "AVAILABLE";
		else
			book_status = "UNAVAILABLE";
		
		Book newBook = new Book(
				request.getId(),
				request.getTitle(), 
				request.getIsbn(), 
				request.getAuthor(), 
				request.getCategory(), 
				request.getDescription(), 
				request.getCoverPicture(), 
				request.getPublicationYear(), 
				request.getEdition(), 
				request.getPublisher(), 
				book_status, 
				request.getQuantity(), 
				request.getMinimumThreshold(), 
				request.getPrice()
				);
		
		// Add to DB
		bookRepository.save(newBook);
		
		// Return response
		BookResponse bookResponse = new BookResponse("Success", null);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	// View book by id
	@GetMapping("/viewBook")
	public ResponseEntity<BookResponse> viewBook(@RequestParam(value = "id") int id) {
		BookResponse response = new BookResponse("Success", null, bookRepository.findById(id));
		return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
	}	
	 
	// Delete Book endpoint
	@GetMapping("/deleteBook")
	public ResponseEntity<BookResponse> deleteBook(@RequestParam(value = "id") int id) {
		bookRepository.delete(bookRepository.findById(id));
		BookResponse response = new BookResponse("Success", null);
		return new ResponseEntity<BookResponse>(response, HttpStatus.OK);
	}
}
