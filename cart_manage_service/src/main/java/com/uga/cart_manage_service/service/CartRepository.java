package com.uga.cart_manage_service.service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.cart_manage_service.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
	
	@Transactional
	public Cart findByAccountId(String accountId);
	
}
