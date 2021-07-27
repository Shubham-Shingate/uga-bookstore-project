package com.uga.cart_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.cart_manage_service.model.CartedBook;

@Repository
public interface CartContents extends CrudRepository<CartedBook, Long> {
	@Transactional
	public List<CartedBook> findByCartId(long cartId);
	@Transactional
	public CartedBook findByCartIdAndBookId(long cartId, long bookId);
	@Transactional
	public void deleteByBookId(long bookId);

}
