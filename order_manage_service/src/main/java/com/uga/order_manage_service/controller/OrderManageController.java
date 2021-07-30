package com.uga.order_manage_service.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.uga.order_manage_service.exception.OrderNotFoundException;
import com.uga.order_manage_service.model.BookEntry;
import com.uga.order_manage_service.model.Order;
import com.uga.order_manage_service.model.OrderBookMapping;
import com.uga.order_manage_service.request.OrderRequest;
import com.uga.order_manage_service.response.OrderResponse;
import com.uga.order_manage_service.service.OrderBookMappingRepository;
import com.uga.order_manage_service.service.OrderRepository;

@RestController
public class OrderManageController {
	
	@Autowired 
	private OrderRepository orderRepository;
	
	@Autowired 
	private OrderBookMappingRepository orderBookMapingRepository;
	
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderResponse> placeOrder(@RequestHeader String accountId, @Validated @RequestBody OrderRequest orderRequest) {
		
		/* Save order entry */
		String orderId = orderRepository.saveOrder(accountId, orderRequest.getCardNumber(), orderRequest.getAddressId(), orderRequest.getTotalCost(), orderRequest.getPromoId(), orderRequest.getDiscountedCost());
		
		/* Save mapping of ordered books */
		BookEntry[] orderedBooks = orderRequest.getBooks();
		for(BookEntry book : orderedBooks) {
			OrderBookMapping orderedBook = new OrderBookMapping(orderId, book.getBookId(), book.getQuantity());
			orderBookMapingRepository.save(orderedBook);
		}
		
		OrderResponse orderResponse = new OrderResponse("Success", null, null);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}
	

	@GetMapping("/getOrderHistory")
	public ResponseEntity<OrderResponse> getOrderHistory(@RequestHeader String accountId) {
		List<Order> orders = orderRepository.findByAccountId(accountId);
		
		if(orders == null || orders.isEmpty()) {
			throw new OrderNotFoundException("There are no past orders associated with the given account ID");
		}
		
		OrderResponse orderResponse = new OrderResponse("Success", null, orders);
		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK); 
	}
	

	@GetMapping("/fetchOrder/{orderId}")
	public ResponseEntity<OrderResponse> fetchOrder(@RequestHeader String accountId, @PathVariable String orderId) {
		
		/* Get order record */
		Order order = orderRepository.findByAccountIdAndOrderId(accountId, orderId);
		
		if(order == null) {
			throw new OrderNotFoundException("There is no order with the given Order ID in this account's order history");
		}		
		
		OrderResponse response = new OrderResponse("Success", null, Arrays.asList(order));
		return new ResponseEntity<OrderResponse>(response, HttpStatus.OK);
	}

}
