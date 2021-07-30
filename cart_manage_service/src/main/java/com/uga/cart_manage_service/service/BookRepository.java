package com.uga.cart_manage_service.service;

import org.springframework.data.repository.CrudRepository;

import com.uga.cart_manage_service.model.CartBook;

public interface BookRepository extends CrudRepository<CartBook, Long> {
	
	public CartBook findByBookId (Long bookId);

}
