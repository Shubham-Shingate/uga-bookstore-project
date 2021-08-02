package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
import com.uga.forwords.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	
	@Transactional
	public Book findById(long id);
}
