package com.uga.book_catalog_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uga.book_catalog_service.model.Book;
import com.uga.book_catalog_service.service.BookRepository;
import com.uga.book_catalog_service.response.*;

@RestController
public class BookCatalogController {

	
	@Autowired
	private BookRepository bookRepository;
	
	
	// @GetMapping("/")
	// public String test() {
	// 	return "Hello";
	// }
	
	
	@GetMapping("/showCatalog")
	public ResponseEntity<CatalogResponse> showBooks() {
		// Execute operation
		List<Book> foundBooks = bookRepository.findAll();
		
		// Return response
		CatalogResponse catalog = new CatalogResponse("Success", null, foundBooks);
		return new ResponseEntity<CatalogResponse>(catalog, HttpStatus.OK);
	}
	
	
}
