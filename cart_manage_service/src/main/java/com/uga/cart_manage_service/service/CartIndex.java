package com.uga.cart_manage_service.service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.cart_manage_service.model.CartMatch;

@Repository
public interface CartIndex extends CrudRepository<CartMatch, Long> {
	
	public CartMatch findByCartId(long id);
	
	public CartMatch findByAccountId(String accountId);
	
}
