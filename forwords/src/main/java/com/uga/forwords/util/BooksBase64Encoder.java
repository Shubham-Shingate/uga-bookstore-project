package com.uga.forwords.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import com.uga.forwords.model.Base64EncodedBook;
import com.uga.forwords.model.Book;
import com.uga.forwords.model.CartBook;
import com.uga.forwords.model.SearchBook;

public class BooksBase64Encoder {

	public static Base64EncodedBook getBase64Encoded (Book book) {
		
		return new Base64EncodedBook(book.getBook_id(), book.getTitle(), book.getIsbn(),
				book.getAuthor(), book.getCategory(), book.getDescription(), Base64.encodeBase64String(ArrayUtils.toPrimitive(book.getCover_picture())),
				book.getPublication_year(), book.getEdition(), book.getPublisher(), book.getBook_status(), book.getQuantity_in_stock(),
				book.getMinimum_threshold(), book.getPrice(), book.getSub_category());
	}
	
	public static Base64EncodedBook getBase64Encoded (SearchBook book) {
		
		return new Base64EncodedBook(book.getBookId(), book.getTitle(), book.getIsbn(),
				book.getAuthor(), book.getCategory(), book.getDescription(), Base64.encodeBase64String(ArrayUtils.toPrimitive(book.getCover_picture())),
				book.getPublication_year(), book.getEdition(), book.getPublisher(), book.getBook_status(), book.getQuantity_in_stock(),
				book.getMinimum_threshold(), book.getPrice(), book.getSub_category());
	}
	
	public static Base64EncodedBook getBase64Encoded(CartBook book) {

		return new Base64EncodedBook(book.getBookId(), book.getTitle(), book.getIsbn(), book.getAuthor(),
				book.getCategory(), book.getDescription(),
				Base64.encodeBase64String(ArrayUtils.toPrimitive(book.getCoverPicture())), book.getPublicationYear(),
				book.getEdition(), book.getPublisher(), book.getBookStatus(), book.getQuantity(),
				book.getMinimumThreshold(), book.getPrice(), book.getSubCategory(), book.getQuantityInCart());
	}
	
}