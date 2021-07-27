package com.uga.search_book_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.search_book_service.model.SearchBook;

@Repository
public interface BookRepository extends CrudRepository<SearchBook, Long> {
	
	@Transactional
	public List<SearchBook> findByIsbnContaining(String isbn);
	
	@Transactional
	public List<SearchBook> findByAuthorContaining(String author);
	
	@Transactional
	public List<SearchBook> findByTitleContaining(String title);
	
	@Transactional
	public List<SearchBook> findByCategoryContaining(String category);
	
	@Transactional
	public List<SearchBook> findByBookId(Long bookId);
	
	@Transactional
	public List<SearchBook> findAll();
	
	
	
}
