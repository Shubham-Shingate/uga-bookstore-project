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
import org.springframework.web.bind.annotation.RestController;
import com.uga.shipping_detail_service.exception.AddressNotFoundException;
import com.uga.shipping_detail_service.model.ShippingEntry;
import com.uga.shipping_detail_service.request.DeleteShipppingDetailsRequest;
import com.uga.shipping_detail_service.request.ShippingInfoRequest;
import com.uga.shipping_detail_service.response.ShippingInfoResponse;
import com.uga.shipping_detail_service.service.ShippingRepository;

@RestController
public class ShippingDetailController {
	
	@Autowired
	private ShippingRepository shippingRepository;

	
	/* ---------------------------Retrieve all addresses for user--------------------------- */
	
	@GetMapping("/getShippingDetails")
	public ResponseEntity<ShippingInfoResponse> getShippingDetails(@RequestHeader String accountId) {
		List<ShippingEntry> addresses = shippingRepository.findByAccountId(accountId);
		ShippingInfoResponse shippingInfoResponse = new ShippingInfoResponse("Success", null, addresses);
		return new ResponseEntity<ShippingInfoResponse>(shippingInfoResponse, HttpStatus.OK);
	}
	
	/* -------Create or update address. If no address_id provided, new address created If address_id is provided, overwrites address at that ID-------- */
	
	@PostMapping("/updateShippingDetails")
	public ResponseEntity<ShippingInfoResponse> updateShippingDetails(@RequestHeader String accountId, @RequestBody @Validated ShippingInfoRequest shippingInfoRequest) {
		ShippingEntry address = new ShippingEntry(shippingInfoRequest.getAddress_id(), shippingInfoRequest.getStreet(),
										shippingInfoRequest.getCity(), shippingInfoRequest.getState(), shippingInfoRequest.getZip_code(),
										shippingInfoRequest.getStatus(), accountId);
		shippingRepository.save(address);
		ShippingInfoResponse shippingInfoResponse = new ShippingInfoResponse("Success", null, null);
		return new ResponseEntity<ShippingInfoResponse>(shippingInfoResponse, HttpStatus.OK);
	}
	
	/* ---------------------------Delete a given addresses for user--------------------------- */
	
	@PostMapping("/deleteShippingDetails")
	public ResponseEntity<ShippingInfoResponse> deleteShippingDetails(@RequestHeader String accountId, @RequestBody @Validated DeleteShipppingDetailsRequest deleteShipppingDetailsRequest)
	{  // Delete address for user based on requestbody = addressId and accounId combination
		List<ShippingEntry> addresses = shippingRepository.findByAccountIdAndAddressId(accountId, deleteShipppingDetailsRequest.getAddressId());
		
		if (addresses.isEmpty()) {
			throw new AddressNotFoundException("No address found for given accountId and addressId combination");
		}
		shippingRepository.deleteById(deleteShipppingDetailsRequest.getAddressId());
		ShippingInfoResponse shippingInfoResponse = new ShippingInfoResponse("Success", null, null);
		return new ResponseEntity<ShippingInfoResponse>(shippingInfoResponse, HttpStatus.OK);
	}
	
}
