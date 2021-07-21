package com.uga.payment_detail_service.exception;

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

import com.uga.payment_detail_service.response.PaymentDetailsResponse;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<PaymentDetailsResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.setTimestamp(LocalDateTime.now());
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<PaymentDetailsResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		apiError.setMessage("Media type is not supported");
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpClientErrorException.BadRequest.class)
	protected ResponseEntity<PaymentDetailsResponse> handleHttpClientErrorExceptionBadRequest(HttpClientErrorException.BadRequest ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Internal service bad request");
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	protected ResponseEntity<PaymentDetailsResponse> handleHttpClientErrorExceptionNotFound(HttpClientErrorException.NotFound ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal service is down");
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<PaymentDetailsResponse> handleDatabaseException(DataAccessException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal Server Error- Failed to access/query/perform transaction in DB");
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<PaymentDetailsResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("One or more mandatory http request headers not provided");
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<PaymentDetailsResponse> handleException(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal Server Error");
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<PaymentDetailsResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("The required HTTP request body was not provided");
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	/**------------------HANDLING OF CUSTOM EXCEPTIONS------------------*/
	@ExceptionHandler(CardExistException.class)
	protected ResponseEntity<PaymentDetailsResponse> handleCardExistException(CardExistException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(CardNotFoundException.class)
	protected ResponseEntity<PaymentDetailsResponse> handleCardNotFoundException(CardNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		PaymentDetailsResponse paymentDetailsResponse = new PaymentDetailsResponse("Failure", apiError, null);
		return new ResponseEntity<PaymentDetailsResponse>(paymentDetailsResponse, HttpStatus.OK);
	}
	
	
	
	
}
