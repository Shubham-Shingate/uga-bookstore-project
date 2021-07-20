package com.uga.book_manage_service.exception;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public  class ApiSubError {

	private String field;
	private Object rejectedValue;
	private String message;

	ApiSubError(String message) {
		this.message = message;
	}

	public ApiSubError(String field2, Object rejectedValue2, String message2) {
		this.message = message2;
		this.field = field2;
		this.rejectedValue = rejectedValue2;

	}
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiSubError [field=" + field + ", rejectedValue=" + rejectedValue + ", message=" + message
				+ ", getField()=" + getField() + ", getRejectedValue()=" + getRejectedValue() + ", getMessage()="
				+ getMessage() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	

}
