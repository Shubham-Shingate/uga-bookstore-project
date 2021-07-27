package com.uga.cart_manage_service.service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.cart_manage_service.model.CartMatch;

@Repository
public interface CartIndex extends CrudRepository<CartMatch, Long> {
	
	@Transactional
	public CartMatch findByCartId(long id);
	
	@Transactional
	public CartMatch findByAccountId(String accountId);
	
}
