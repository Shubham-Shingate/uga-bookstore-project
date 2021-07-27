package com.uga.order_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.order_manage_service.model.OrderedBook;

@Repository
public interface OrderContents extends CrudRepository<OrderedBook, Long> {
	
	@Transactional
	public List<OrderedBook> findByOrderId(String orderId);
	@Transactional
	public OrderedBook findByBookIdAndOrderId(Long bookId, String orderId);

}
