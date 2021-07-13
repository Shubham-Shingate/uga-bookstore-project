package com.uga.search_book_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.search_book_service.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	public List<Book> findByTitleContaining(String title);
	
	List<Book> findAll();
}
