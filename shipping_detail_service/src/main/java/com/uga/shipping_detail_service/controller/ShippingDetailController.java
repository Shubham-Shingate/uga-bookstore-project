package com.uga.shipping_detail_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uga.shipping_detail_service.model.ShippingEntry;
import com.uga.shipping_detail_service.request.AddressRequest;
import com.uga.shipping_detail_service.service.ShippingRepository;
import com.uga.shipping_detail_service.response.*;

@RestController
public class ShippingDetailController {
	
	@Autowired
	private ShippingRepository shippingRepository;
	
//	// Create new address for user
//	@PostMapping("/saveNewAddress")
//	public ResponseEntity<ShippingResponse> saveNewAddress(@RequestHeader("accountId") String accountId, @RequestBody AddressRequest request) {
//		ShippingEntry address = new ShippingEntry(request.getStreet(), request.getCity(), request.getState(), request.getZip_code(), request.getStatus(), accountId);
//		shippingRepository.save(address);
//		return new ResponseEntity<ShippingResponse>(new ShippingResponse("New address added successfully", null), HttpStatus.OK);
//	}
	
	// Retrieve all addresses for user
	@PostMapping("/fetchShippingDetails")
	public ResponseEntity<ShippingResponse> fetchShippingDetails(@RequestHeader("accountId") String accountId) {
		List<ShippingEntry> addresses = shippingRepository.findByAccountId(accountId);
		ShippingResponse response = new ShippingResponse("Fetch success", null, addresses);
		return new ResponseEntity<ShippingResponse>(response, HttpStatus.OK);
	}
	
	// Create or update address
	// If no address_id provided, new address created
	// If address_id is provided, overwrites address at that ID
	@PostMapping("/updateAddress")
	public ResponseEntity<ShippingResponse> updateAddress(@RequestHeader("accountId") String accountId, @RequestBody @Validated AddressRequest request) {
		ShippingEntry address = new ShippingEntry(request.getAddress_id(), request.getStreet(), request.getCity(), request.getState(), request.getZip_code(), request.getStatus(), accountId);
		shippingRepository.save(address);
		return new ResponseEntity<ShippingResponse>(new ShippingResponse("Address updated successfully", null), HttpStatus.OK);
	}
	
	// Delete address for user based on requestbody = addressId
	@PostMapping("/deleteAddress")
	public ResponseEntity<ShippingResponse> deleteAddress(@RequestHeader("accountId") String accountId, @RequestBody @Validated AddressRequest request)
	{
		shippingRepository.delete(shippingRepository.findById(request.getAddress_id()));
		ShippingResponse response = new ShippingResponse("Address deleted successfully", null);
		return new ResponseEntity<ShippingResponse>(response, HttpStatus.OK);
	}
}
