package com.uga.search_book_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uga.search_book_service.model.Book;
import com.uga.search_book_service.service.BookRepository;

@RestController
public class SearchBookController {

	
	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping("/")
	public String test() {
		return "Hello";
	}
	
	
	@GetMapping("/searchBooks/{title}")
	public ResponseEntity<List<Book>> searchBooks(@PathVariable String title) {
		List<Book> foundBooks = bookRepository.findByTitleContaining(title);
		return new ResponseEntity<List<Book>>(foundBooks, HttpStatus.OK);
	}
	
	
}
