package com.uga.cart_manage_service.controller;

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

import com.uga.cart_manage_service.model.CartMatch;
import com.uga.cart_manage_service.model.CartedBook;
import com.uga.cart_manage_service.request.CartUpdateRequest;
import com.uga.cart_manage_service.response.CartResponse;
import com.uga.cart_manage_service.service.CartContents;
import com.uga.cart_manage_service.service.CartIndex;
import com.uga.cart_manage_service.exception.BookNotFoundException;
import com.uga.cart_manage_service.exception.CartNotFoundException;
import com.uga.cart_manage_service.exception.InvalidQuantityException;

@RestController
public class CartManagementController {
	
	/* Master index matching accounts to carts */
	@Autowired 
	private CartIndex cartIndex;
	
	/* Mapping logical books to carts */
	@Autowired
	private CartContents cartContents;
	
	/* Add book to cart */
	
	
	/* View cart */
	@GetMapping("/fetchUserCart")
	public ResponseEntity<CartResponse> fetchUserCart(@RequestHeader String accountId) {
		
		/* Fetch cart ID associated with this account */
		CartMatch cart = cartIndex.findByAccountId(accountId);
		
		if(cart == null)
			throw new CartNotFoundException("There is no cart that matches the given Account ID");
		
		
		/* Fetch cart contents from DB */
		List<CartedBook> books = cartContents.findByCartId(cart.getCartId());
		
		
		
		CartResponse response = new CartResponse("Success", null, books);
		return new ResponseEntity<CartResponse>(response, HttpStatus.OK);
	}
	
	/* Create or Update book quantity 
	 * If book is in cart, quantity updated
	 * If book isn't in cart, is added to cart with given quantity*/
	@PostMapping("/updateBookEntry")
	public ResponseEntity<CartResponse> updateBookEntry(@RequestHeader int cartId, @RequestBody @Validated CartUpdateRequest request) {
		
		/* Verify that this cart exists */
		CartMatch cart = cartIndex.findByCartId(cartId);
		if(cart == null)
			throw new CartNotFoundException("The given cart does not exist!");
		
		
		CartedBook cartEntry = new CartedBook(cartId, request.getBookId(), request.getQty());
		
		if(request.getQty() < 1)
			throw new InvalidQuantityException("Quantity must be greater than 0");
		
		cartContents.save(cartEntry);
		
		CartResponse response = new CartResponse("Success", null);
		return new ResponseEntity<CartResponse>(response, HttpStatus.OK);
	}
	
//	/* Remove book from cart */
	@GetMapping("/removeBook/{bookId}")
	public ResponseEntity<CartResponse> removeBook(@RequestHeader int cartId, @PathVariable long bookId) {
		CartedBook book = cartContents.findByCartIdAndBookId(cartId, bookId);
		
		// Ensure entry exists
		if(book != null)
			cartContents.delete(book);
		else
			throw new BookNotFoundException("This book is not in your cart!");
		
		
		CartResponse response = new CartResponse("Success", null);
		return new ResponseEntity<CartResponse>(response, HttpStatus.OK);
	}
	
	
}
