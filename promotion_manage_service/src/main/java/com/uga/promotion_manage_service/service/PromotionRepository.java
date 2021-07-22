/**
 * 
 */
package com.uga.promotion_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.promotion_manage_service.model.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, String> {
	
	public Promotion findByPromoId(String promoId);
	
	public List<Promotion> findAll();

}
