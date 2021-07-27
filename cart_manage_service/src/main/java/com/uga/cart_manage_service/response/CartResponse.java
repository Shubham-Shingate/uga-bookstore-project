package com.uga.cart_manage_service.response;

import java.util.List;

import com.uga.cart_manage_service.model.CartedBook;

public class CartResponse {

		private String message;
		
		private Object apiError;
		
		private List<CartedBook> books;

		/**
		 * @param message
		 * @param apiError
		 */
		public CartResponse(String message, Object apiError) {
			this.message = message;
			this.apiError = apiError;
		}

		/**
		 * @param message
		 * @param apiError
		 * @param books
		 */
		public CartResponse(String message, Object apiError, List<CartedBook> books) {
			this.message = message;
			this.apiError = apiError;
			this.books = books;
		}

		@Override
		public String toString() {
			return "CartResponse [message=" + message + ", apiError=" + apiError + ", books=" + books + "]";
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}

		/**
		 * @return the apiError
		 */
		public Object getApiError() {
			return apiError;
		}

		/**
		 * @param apiError the apiError to set
		 */
		public void setApiError(Object apiError) {
			this.apiError = apiError;
		}

		/**
		 * @return the books
		 */
		public List<CartedBook> getBooks() {
			return books;
		}

		/**
		 * @param books the books to set
		 */
		public void setBooks(List<CartedBook> books) {
			this.books = books;
		}
		
		
}
