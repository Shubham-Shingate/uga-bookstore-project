package com.uga.book_manage_service.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.book_manage_service.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	@Transactional
	public Book findById(int id);

}
