package com.uga.search_book_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.search_book_service.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	public List<Book> findByIsbnContaining(String isbn);
	
	public List<Book> findByAuthorContaining(String author);
	
	public List<Book> findByTitleContaining(String title);
	
	public List<Book> findByCategoryContaining(String category);
	
	public List<Book> findByBookId(Long bookId);
	
	public List<Book> findAll();
	
	
	
}
