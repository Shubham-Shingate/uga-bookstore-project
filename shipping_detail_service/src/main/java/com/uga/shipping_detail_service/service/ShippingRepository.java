package com.uga.shipping_detail_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.shipping_detail_service.model.*;

@Repository
public interface ShippingRepository extends CrudRepository<ShippingEntry, Long> {
	
	@Transactional
	public List<ShippingEntry> findByAccountId(String accountId);
	
	@Transactional
	public List<ShippingEntry> findByAccountIdAndAddressId(String accountId, Long addressId);
	
	
}
