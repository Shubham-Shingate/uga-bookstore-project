package com.uga.book_manage_service.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

	private HttpStatus status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	private String message;
	
	@JsonTypeInfo(use = JsonTypeInfo.Id.NONE, include = JsonTypeInfo.As.PROPERTY, property = "className")
	private List<ApiSubError> subErrors;

	public ApiError(HttpStatus status) {
		this.status = status;
	}

	public ApiError(HttpStatus unsupportedMediaType, String string, LocalDateTime now) {
		this.status=unsupportedMediaType;
		this.message=string;
		this.timestamp=now;
		
	}

	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}

	private void addValidationError(FieldError fieldError) {
		this.addValidationError(fieldError.getField(),
				fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
	}

	private void addValidationError(String field, Object rejectedValue, String message) {
		addSubError(new ApiSubError(field, rejectedValue, message));
	}

	private void addSubError(ApiSubError subError) {
		if (subErrors == null) {
			subErrors = new ArrayList<>();
		}
		subErrors.add(subError);
	}

	public void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiError [status=" + status + ", timestamp=" + timestamp + ", message=" + message + ", subErrors="
				+ subErrors + "]";
	}

}
