package com.uga.promotion_manage_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.uga.promotion_manage_service.exception.PromoExistsException;
import com.uga.promotion_manage_service.exception.PromotionNotFoundException;
import com.uga.promotion_manage_service.model.Promotion;
import com.uga.promotion_manage_service.request.PromotionInfoRequest;
import com.uga.promotion_manage_service.response.FetchAllPromotionsResponse;
import com.uga.promotion_manage_service.response.PromotionInfoResponse;
import com.uga.promotion_manage_service.service.PromotionRepository;

@RestController
public class PromotionManagementController {
	
	@Autowired
	private PromotionRepository repository;
	
	/* Retrieve all promotions */
	@GetMapping("/fetchAllPromotions")
	public ResponseEntity<FetchAllPromotionsResponse> fetchAllPromotions() {
		List<Promotion> promotions = repository.findAll();
		
		FetchAllPromotionsResponse response = new FetchAllPromotionsResponse("Success", null, promotions);
		return new ResponseEntity<FetchAllPromotionsResponse>(response, HttpStatus.OK);
	}
	
	/* Create a promotion */
	@PostMapping("/updatePromotion")
	public ResponseEntity<PromotionInfoResponse> updatePromotion(@RequestBody @Validated PromotionInfoRequest request) {
		
		// Check if promoID already exists		
		if(repository.findByPromoId(request.getPromoId()) != null)
			throw new PromoExistsException("A promotion with this ID already exists!");
		
		// Convert Strings to Timestamps for storage		
		Promotion promo = new Promotion(
				request.getPromoId(),
				request.getStartTimestamp(),
				request.getEndTimestamp(),
				request.getDiscountValue());
		
		repository.save(promo);		
		PromotionInfoResponse response = new PromotionInfoResponse("Success", null);
		return new ResponseEntity<PromotionInfoResponse>(response, HttpStatus.OK);
	}
	
	/*View a promotion*/
	@PostMapping("/viewPromotion/{promoId}")
	public ResponseEntity<PromotionInfoResponse> viewPromotion(@PathVariable String promoId) {
		Promotion promo = repository.findByPromoId(promoId);
		
		if(promo == null ) {
			throw new PromotionNotFoundException("There is no promotion with this id");
		}
		
		PromotionInfoResponse response = new PromotionInfoResponse("Success", null, promo);
		return new ResponseEntity<PromotionInfoResponse>(response, HttpStatus.OK);
	}
	
	/* Delete a promotion */
	@PostMapping("/deletePromotion/{promoId}")
	public ResponseEntity<PromotionInfoResponse> deletePromotion(@PathVariable String promoId) {
		Promotion promo = repository.findByPromoId(promoId);
		
		if(promo == null) {
			throw new PromotionNotFoundException("There is no promotion with this id");
		}
		repository.delete(promo);
		PromotionInfoResponse response = new PromotionInfoResponse("Success", null);
		return new ResponseEntity<PromotionInfoResponse>(response, HttpStatus.OK);
	}
}
