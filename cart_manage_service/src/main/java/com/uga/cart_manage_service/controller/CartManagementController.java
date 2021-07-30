package com.uga.cart_manage_service.controller;

import java.util.ArrayList;
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
import com.uga.cart_manage_service.exception.BookNotFoundException;
import com.uga.cart_manage_service.exception.BookQtyInsufficientException;
import com.uga.cart_manage_service.exception.CartNotFoundException;
import com.uga.cart_manage_service.exception.InvalidQuantityException;
import com.uga.cart_manage_service.model.CartBook;
import com.uga.cart_manage_service.model.Cart;
import com.uga.cart_manage_service.model.CartBookMapping;
import com.uga.cart_manage_service.request.CartUpdateRequest;
import com.uga.cart_manage_service.response.CartResponse;
import com.uga.cart_manage_service.service.BookRepository;
import com.uga.cart_manage_service.service.CartBookMappingRepository;
import com.uga.cart_manage_service.service.CartRepository;

@RestController
public class CartManagementController {
	
	@Autowired 
	private CartRepository cartRepository;

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CartBookMappingRepository cartBookMappingRepository;
	
	/* get user cart */
	@GetMapping("/getCartDetails")
	public ResponseEntity<CartResponse> getCartDetails(@RequestHeader String accountId) {
		
		Cart cart = cartRepository.findByAccountId(accountId);
		if(cart == null) {
			throw new CartNotFoundException("There is no cart that matches the given Account ID");
		}
		//Fetch cart-book mappings from DB
		List<CartBookMapping> cartBookMappings = cartBookMappingRepository.findByCartId(cart.getCartId());
//		if (cartBookMappings.isEmpty()) {
//			throw new EmptyCartException("There are no items in this particular cart");
//		}
		
		//Fetch the book details for book id's mapped to cart
		List<CartBook> books = new ArrayList<CartBook>();
		
		for (CartBookMapping cartBookMapping : cartBookMappings) {
			books.add(bookRepository.findByBookId(cartBookMapping.getBookId()));
		}
		
		CartResponse cartResponse = new CartResponse("Success", null, books);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	
	/* Create or Update book quantity 
	 * If book is in cart, quantity updated
	 * If book isn't in cart, is added to cart with given quantity*/
	@PostMapping("/addUpdateBookToCart")
	public ResponseEntity<CartResponse> addUpdateBookToCart(@RequestHeader String accountId , @RequestBody @Validated CartUpdateRequest cartUpdateRequest) {
		
		if(cartUpdateRequest.getQty() < 1) {
			throw new InvalidQuantityException("Requested Quantity must be greater than 0");
		}
		
		Cart cart = cartRepository.findByAccountId(accountId);
		if(cart == null)
			throw new CartNotFoundException("There is no cart that matches the given Account ID");
		
		//Check if book is available in the inventory
		CartBook book = bookRepository.findByBookId(cartUpdateRequest.getBookId());
		if (book.getQuantity() == null || book.getQuantity() <= 0) {
			throw new BookQtyInsufficientException("The book mentioned is out if stock");
		}
		
		CartBookMapping cartEntry = new CartBookMapping(cart.getCartId(), cartUpdateRequest.getBookId(), cartUpdateRequest.getQty());
		cartBookMappingRepository.save(cartEntry);
		CartResponse cartResponse = new CartResponse("Success", null, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	
	/* Remove book from cart */
	@GetMapping("/removeBook/{bookId}")
	public ResponseEntity<CartResponse> removeBook(@RequestHeader String accountId, @PathVariable Long bookId) {
		
		Cart cart = cartRepository.findByAccountId(accountId);
		if(cart == null) {
			throw new CartNotFoundException("There is no cart that matches the given Account ID");
		}
		
		CartBookMapping cartEntry = cartBookMappingRepository.findByCartIdAndBookId(cart.getCartId(), bookId);
		if(cartEntry != null) {
			cartBookMappingRepository.delete(cartEntry);
		} else {
			throw new BookNotFoundException("This book is not in your cart!");
		}
		
		CartResponse cartResponse = new CartResponse("Success", null, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	/*
	 * Empty user's cart
	 * To be used after an order has been completed, and the cart needs to be reset
	 */	
	@GetMapping("/emptyCart")
	public ResponseEntity<CartResponse> emptyCart(@RequestHeader String accountId) {
		
		Cart cart = cartRepository.findByAccountId(accountId);
		if(cart == null) {
			throw new CartNotFoundException("There is no cart that matches the given Account ID");
		}
		
		List<CartBookMapping> booksInCart = cartBookMappingRepository.findByCartId(cart.getCartId());
		cartBookMappingRepository.deleteAll(booksInCart);
		
		CartResponse cartResponse = new CartResponse("Success", null, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
}
