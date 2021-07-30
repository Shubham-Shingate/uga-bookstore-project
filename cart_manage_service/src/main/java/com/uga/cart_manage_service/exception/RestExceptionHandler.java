package com.uga.cart_manage_service.exception;

import java.time.LocalDateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import com.uga.cart_manage_service.response.CartResponse;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<CartResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.setTimestamp(LocalDateTime.now());
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<CartResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		apiError.setMessage("Media type is not supported");
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpClientErrorException.BadRequest.class)
	protected ResponseEntity<CartResponse> handleHttpClientErrorExceptionBadRequest(HttpClientErrorException.BadRequest ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Internal service bad request");
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	protected ResponseEntity<CartResponse> handleHttpClientErrorExceptionNotFound(HttpClientErrorException.NotFound ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal service is down");
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<CartResponse> handleDatabaseException(DataAccessException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal Server Error- Failed to access/query/perform transaction in DB");
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<CartResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("One or more mandatory http request headers not provided");
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<CartResponse> handleException(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal Server Error");
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<CartResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("The required HTTP request body was not provided");
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	/**------------------HANDLING OF CUSTOM EXCEPTIONS------------------*/
	@ExceptionHandler(InvalidQuantityException.class)
	protected ResponseEntity<CartResponse> handleInvalidQuantityException(InvalidQuantityException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	protected ResponseEntity<CartResponse> handleBookNotFoundException(BookNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	protected ResponseEntity<CartResponse> handleCartNotFoundException(CartNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(BookQtyInsufficientException.class)
	protected ResponseEntity<CartResponse> handleBookQtyInsufficientException(BookQtyInsufficientException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(EmptyCartException.class)
	protected ResponseEntity<CartResponse> handleEmptyCartException(EmptyCartException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		CartResponse cartResponse = new CartResponse("Failure", apiError, null);
		return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
	}
	
	
}
