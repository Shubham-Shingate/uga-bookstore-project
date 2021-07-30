package com.uga.order_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.order_manage_service.model.OrderBookMapping;

@Repository
public interface OrderBookMappingRepository extends CrudRepository<OrderBookMapping, Long> {
	
	@Transactional
	public List<OrderBookMapping> findByOrderId(String orderId);
	
	@Transactional
	public OrderBookMapping findByBookIdAndOrderId(Long bookId, String orderId);

}
