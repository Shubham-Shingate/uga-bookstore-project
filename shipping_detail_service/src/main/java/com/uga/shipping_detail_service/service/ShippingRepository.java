package com.uga.shipping_detail_service.service;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.shipping_detail_service.model.*;

@Repository
public interface ShippingRepository extends CrudRepository<ShippingEntry, Integer> {
		
	public ShippingEntry findById(int id);
	
	// Each account should have one address, modify this if functionality expanded
	public List<ShippingEntry> findByAccountId(String account_id);
	
	public List<ShippingEntry> findAll();
	
	//public ShippingEntry update(ShippingEntry address);
}
