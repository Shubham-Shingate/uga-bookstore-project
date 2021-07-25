package com.uga.book_catalog_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.book_catalog_service.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	public List<Book> findByTitleContaining(String title);
	
	List<Book> findAll();
}
