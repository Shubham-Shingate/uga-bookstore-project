package com.uga.book_manage_service.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
import com.uga.book_manage_service.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	@Transactional
	public Book findById(long id);
}
