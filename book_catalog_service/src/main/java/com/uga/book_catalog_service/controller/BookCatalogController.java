package com.uga.book_catalog_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uga.book_catalog_service.model.Book;
import com.uga.book_catalog_service.service.BookRepository;

@RestController
public class BookCatalogController {

	
	@Autowired
	private BookRepository bookRepository;
	
	
	// @GetMapping("/")
	// public String test() {
	// 	return "Hello";
	// }
	
	
	@GetMapping("/showCatalog")
	public ResponseEntity<List<Book>> showBooks() {
		List<Book> foundBooks = bookRepository.findAll();
		return new ResponseEntity<List<Book>>(foundBooks, HttpStatus.OK);
	}
	
	
}
