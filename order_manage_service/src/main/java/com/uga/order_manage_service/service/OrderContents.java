package com.uga.order_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uga.order_manage_service.model.OrderedBook;

@Repository
public interface OrderContents extends CrudRepository<OrderedBook, Long> {
	
	public List<OrderedBook> findByOrderId(String orderId);
	
	public OrderedBook findByBookIdAndOrderId(Long bookId, String orderId);

}
