package com.uga.book_manage_service.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.book_manage_service.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	public Book findById(int id);
}
