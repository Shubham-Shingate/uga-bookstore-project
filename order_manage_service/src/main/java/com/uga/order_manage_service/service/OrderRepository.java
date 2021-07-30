package com.uga.order_manage_service.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.order_manage_service.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
	
	@Transactional
	public List<Order> findByAccountId(String account_id);
	
	@Transactional
	public Order findByAccountIdAndOrderId(String account_id, String order_id);

	@Transactional
	@Query(value = "CALL SP_CREATE_ORDER(:in_ACCOUNT_ID, :in_CARD_NUMBER, :in_ADDRESS_ID, :in_TOTAL_COST, :in_PROMO_ID, :in_DISCOUNTED_COST);", nativeQuery = true)
	public String saveOrder(
			@Param("in_ACCOUNT_ID") String in_ACCOUNT_ID,
			@Param("in_CARD_NUMBER") String in_CARD_NUMBER, 
			@Param("in_ADDRESS_ID") Long in_ADDRESS_ID, 
			@Param("in_TOTAL_COST") Double in_TOTAL_COST,
			@Param("in_PROMO_ID") String in_PROMO_ID,
			@Param("in_DISCOUNTED_COST") Double in_DISCOUNTED_COST);

}
