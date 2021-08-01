package com.uga.cart_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.cart_manage_service.model.CartBookMapping;

@Repository
public interface CartBookMappingRepository extends CrudRepository<CartBookMapping, Long> {
	
	@Transactional
	public List<CartBookMapping> findByCartId(Long cartId);
	
	@Transactional
	public CartBookMapping findByCartIdAndBookId(Long cartId, Long bookId);
	
	@Transactional
	public void deleteByBookId(Long bookId);

}
