package com.uga.search_book_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.search_book_service.model.SearchBook;

@Repository
public interface BookRepository extends CrudRepository<SearchBook, Long> {
	
	public List<SearchBook> findByIsbnContaining(String isbn);
	
	public List<SearchBook> findByAuthorContaining(String author);
	
	public List<SearchBook> findByTitleContaining(String title);
	
	public List<SearchBook> findByCategoryContaining(String category);
	
	public List<SearchBook> findByBookId(Long bookId);
	
	public List<SearchBook> findAll();
	
	
	
}
