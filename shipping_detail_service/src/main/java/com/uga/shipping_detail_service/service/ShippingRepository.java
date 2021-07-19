package com.uga.shipping_detail_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.shipping_detail_service.model.*;

@Repository
public interface ShippingRepository extends CrudRepository<ShippingEntry, Long> {
	
	public List<ShippingEntry> findByAccountId(String accountId);
	
	public List<ShippingEntry> findByAccountIdAndAddressId(String accountId, Long addressId);
	
	
}
