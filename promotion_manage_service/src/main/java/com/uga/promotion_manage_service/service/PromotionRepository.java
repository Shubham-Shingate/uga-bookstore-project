/**
 * 
 */
package com.uga.promotion_manage_service.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uga.promotion_manage_service.model.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, String> {
	@Transactional
	public Promotion findByPromoId(String promoId);
	@Transactional
	public List<Promotion> findAll();

}
