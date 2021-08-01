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

import java.util.ArrayList;
import java.util.List;

import com.uga.promotion_manage_service.exception.InvalidDatesException;
import com.uga.promotion_manage_service.exception.PromoExistsException;
import com.uga.promotion_manage_service.exception.PromotionNotFoundException;
import com.uga.promotion_manage_service.model.Promotion;
import com.uga.promotion_manage_service.request.PromotionInfoRequest;
import com.uga.promotion_manage_service.response.PromotionInfoResponse;
import com.uga.promotion_manage_service.service.PromotionRepository;

@RestController
public class PromotionManagementController {
	
	@Autowired
	private PromotionRepository repository;
	
	/* Retrieve all promotions */
	@GetMapping("/fetchAllPromotions")
	public ResponseEntity<PromotionInfoResponse> fetchAllPromotions() {
		List<Promotion> promotions = repository.findAll();
		
		PromotionInfoResponse promotionManageResponse = new PromotionInfoResponse("Success", null, promotions);
		return new ResponseEntity<PromotionInfoResponse>(promotionManageResponse, HttpStatus.OK);
	}
	
	/* Create a promotion */
	@PostMapping("/createPromotion")
	public ResponseEntity<PromotionInfoResponse> createPromotion(@RequestBody @Validated PromotionInfoRequest request) {

		if (repository.findByPromoId(request.getPromoId()) != null)
			throw new PromoExistsException("A promotion with this ID already exists!");

		if (request.getStartTimestamp().after(request.getEndTimestamp()) == true)
			throw new InvalidDatesException("Start date cannot be after expiration date");

		Promotion promo = new Promotion(request.getPromoId(), request.getStartTimestamp(), request.getEndTimestamp(),
										request.getDiscountValue());

		repository.save(promo);
		PromotionInfoResponse promotionManageResponse = new PromotionInfoResponse("Success", null);
		return new ResponseEntity<PromotionInfoResponse>(promotionManageResponse, HttpStatus.OK);
	}
	
	/*View a promotion*/
	@GetMapping("/viewPromotion/{promoId}")
	public ResponseEntity<PromotionInfoResponse> viewPromotion(@PathVariable String promoId) {
		Promotion promo = repository.findByPromoId(promoId);
		
		if(promo == null ) {
			throw new PromotionNotFoundException("There is no promotion with this id");
		}
		
		List<Promotion> promotions = new ArrayList<Promotion>();
		promotions.add(promo);
		
		PromotionInfoResponse promotionManageResponse = new PromotionInfoResponse("Success", null, promotions);
		return new ResponseEntity<PromotionInfoResponse>(promotionManageResponse, HttpStatus.OK);
	}
	
	/* Delete a promotion */
	@GetMapping("/deletePromotion/{promoId}")
	public ResponseEntity<PromotionInfoResponse> deletePromotion(@PathVariable String promoId) {
		Promotion promo = repository.findByPromoId(promoId);
		
		/* Ensure promotion exists before attempting deletion */
		if(promo == null) 
			throw new PromotionNotFoundException("There is no promotion with this id");
		
		repository.delete(promo);
		PromotionInfoResponse promotionManageResponse = new PromotionInfoResponse("Success", null);
		return new ResponseEntity<PromotionInfoResponse>(promotionManageResponse, HttpStatus.OK);
	}
}
