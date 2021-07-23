package com.uga.validate_token_service.exception;

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
import com.uga.validate_token_service.response.ValidateTokenResponse;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ValidateTokenResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.setTimestamp(LocalDateTime.now());
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	protected ResponseEntity<ValidateTokenResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		apiError.setMessage("Media type is not supported");
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpClientErrorException.BadRequest.class)
	protected ResponseEntity<ValidateTokenResponse> handleHttpClientErrorExceptionBadRequest(HttpClientErrorException.BadRequest ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Internal service bad request");
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	protected ResponseEntity<ValidateTokenResponse> handleHttpClientErrorExceptionNotFound(HttpClientErrorException.NotFound ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal service is down");
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<ValidateTokenResponse> handleDatabaseException(DataAccessException ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal Server Error- Failed to access/query/perform transaction in DB");
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<ValidateTokenResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("One or more mandatory http request headers not provided");
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ValidateTokenResponse> handleException(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Internal Server Error");
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ValidateTokenResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("The required HTTP request body was not provided");
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	/**------------------HANDLING OF CUSTOM EXCEPTIONS------------------*/
	@ExceptionHandler(ExpiredTokenException.class)
	protected ResponseEntity<ValidateTokenResponse> handleExpiredTokenException(ExpiredTokenException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(InactiveTokenException.class)
	protected ResponseEntity<ValidateTokenResponse> handleInactiveTokenException(InactiveTokenException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(TokenNotFoundException.class)
	protected ResponseEntity<ValidateTokenResponse> handleTokenNotFoundException(TokenNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.OK);
		apiError.setMessage(ex.getMessage());
		apiError.setTimestamp(LocalDateTime.now());
		
		ValidateTokenResponse validateTokenResponse = new ValidateTokenResponse("Failure", apiError);
		return new ResponseEntity<ValidateTokenResponse>(validateTokenResponse, HttpStatus.OK);
	}
	
	
}
