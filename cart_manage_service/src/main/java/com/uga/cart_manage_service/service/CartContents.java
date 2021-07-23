package com.uga.cart_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.cart_manage_service.model.CartedBook;

@Repository
public interface CartContents extends CrudRepository<CartedBook, Long> {
	
	public List<CartedBook> findByCartId(long cartId);
	
	public CartedBook findByCartIdAndBookId(long cartId, long bookId);
	
	public void deleteByBookId(long bookId);

}
