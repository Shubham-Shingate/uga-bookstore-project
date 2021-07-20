package com.uga.search_book_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uga.search_book_service.exception.NoBooksFoundException;
import com.uga.search_book_service.model.Book;
import com.uga.search_book_service.response.SearchBookResponse;
import com.uga.search_book_service.service.BookRepository;

@RestController
public class SearchBookController {
	
	@Autowired
	private BookRepository bookRepository;

	
	@GetMapping("/searchBooksTitle/{title}")
	public ResponseEntity<SearchBookResponse> searchBooksTitle(@PathVariable String title) {
		List<Book> foundBooks = bookRepository.findByTitleContaining(title);
		if (foundBooks.isEmpty()) {
			throw new NoBooksFoundException("No book found for given title- "+title);
		}
		
		SearchBookResponse searchBookResponse = new SearchBookResponse("Success", null, foundBooks);
		return new ResponseEntity<SearchBookResponse>(searchBookResponse, HttpStatus.OK);
	}
	
	@GetMapping("/searchBooksISBN/{isbn}")
	public ResponseEntity<SearchBookResponse> searchBooksISBN(@PathVariable String isbn) {
		List<Book> foundBooks = bookRepository.findByIsbnContaining(isbn);
		if (foundBooks.isEmpty()) {
			throw new NoBooksFoundException("No book found for given isbn- "+isbn);
		}
		
		SearchBookResponse searchBookResponse = new SearchBookResponse("Success", null, foundBooks);
		return new ResponseEntity<SearchBookResponse>(searchBookResponse, HttpStatus.OK);
	}
	
	@GetMapping("/searchBooksAuthor/{author}")
	public ResponseEntity<SearchBookResponse> searchBooksAuthor(@PathVariable String author) {
		List<Book> foundBooks = bookRepository.findByAuthorContaining(author);
		if (foundBooks.isEmpty()) {
			throw new NoBooksFoundException("No book found for given author- "+author);
		}
		
		SearchBookResponse searchBookResponse = new SearchBookResponse("Success", null, foundBooks);
		return new ResponseEntity<SearchBookResponse>(searchBookResponse, HttpStatus.OK);
	}
	
	@GetMapping("/searchBooksCategory/{category}")
	public ResponseEntity<SearchBookResponse> searchBooksCategory(@PathVariable String category) {
		List<Book> foundBooks = bookRepository.findByCategoryContaining(category);
		if (foundBooks.isEmpty()) {
			throw new NoBooksFoundException("No book found for given category- "+category);
		}
		
		SearchBookResponse searchBookResponse = new SearchBookResponse("Success", null, foundBooks);
		return new ResponseEntity<SearchBookResponse>(searchBookResponse, HttpStatus.OK);
	}
	
	
	@GetMapping("/searchBooksBookId/{bookId}")
	public ResponseEntity<SearchBookResponse> searchBooksBookId(@PathVariable Long bookId) {
		List<Book> foundBooks = bookRepository.findByBookId(bookId);
		if (foundBooks.isEmpty()) {
			throw new NoBooksFoundException("No book found for given bookId- "+bookId);
		}
		
		SearchBookResponse searchBookResponse = new SearchBookResponse("Success", null, foundBooks);
		return new ResponseEntity<SearchBookResponse>(searchBookResponse, HttpStatus.OK);
	}
	
	//NOTE: For finding all the books, query the book_catalog_service instead of coming here.
	
	
}
