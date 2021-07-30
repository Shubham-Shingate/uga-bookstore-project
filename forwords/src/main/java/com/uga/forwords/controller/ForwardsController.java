package com.uga.forwords.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import com.uga.forwords.model.ActiveUser;
import com.uga.forwords.model.Base64EncodedBook;
import com.uga.forwords.model.Book;
import com.uga.forwords.model.CartBook;
import com.uga.forwords.model.Config;
import com.uga.forwords.model.PaymentCard;
import com.uga.forwords.model.SearchBook;
import com.uga.forwords.model.ShippingEntry;
import com.uga.forwords.request.AddPaymentDetailsRequest;
import com.uga.forwords.request.CartUpdateRequest;
import com.uga.forwords.request.ChangePasswordRequest;
import com.uga.forwords.request.DeletePaymentDetailsRequest;
import com.uga.forwords.request.DeleteShipppingDetailsRequest;
import com.uga.forwords.request.EmailRequest;
import com.uga.forwords.request.PlaceOrderRequest;
import com.uga.forwords.request.ShippingInfoRequest;
import com.uga.forwords.request.UpdatePaymentDetailsRequest;
import com.uga.forwords.request.UpdateProfileDetailsRequest;
import com.uga.forwords.response.CartResponse;
import com.uga.forwords.response.CatalogResponse;
import com.uga.forwords.response.EmailResponse;
import com.uga.forwords.response.PaymentDetailsResponse;
import com.uga.forwords.response.PersonalDetailsResponse;
import com.uga.forwords.response.SearchBookResponse;
import com.uga.forwords.response.ShippingInfoResponse;
import com.uga.forwords.service.ActiveUserRepository;
import com.uga.forwords.service.ConfigRepository;
import com.uga.forwords.util.BooksBase64Encoder;

@Controller
public class ForwardsController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private ActiveUserRepository activeUserRepository;
	
	@Autowired
	private ConfigRepository configRepository;
	
	private Map<String, String> applicationConfig = new HashMap<String, String>();
	
	@PostConstruct
	private void loadConfig() {
		List<Config> configs = (List<Config>) configRepository.findAll();
		for (Config config : configs) {
			applicationConfig.put(config.getConfigKey(), config.getConfigValue());
		}
	}
	
	public String sendEmail(String accountId, String emailPurposeBody) { // Utility method to trigger emails via email_service
		
		//Get authenticated users email ID from the DB (This can be done by also calling profile_detail_service, but just to avoid 1 network call we fetch it here) This is not good practise!
		ActiveUser activeUser = activeUserRepository.findByAccountId(accountId);
		
		//Make an API call to email_service to send email
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		EmailRequest emailRequest = new EmailRequest("UPDATE CONFIRMATION EMAIL", applicationConfig.get(emailPurposeBody), activeUser.getEmailId());
		HttpEntity<EmailRequest> entity = new HttpEntity<EmailRequest>(emailRequest, headers);
		ResponseEntity<EmailResponse> emailServiceResponse = 
					restTemplate.postForEntity("http://email-service/emailService", entity, EmailResponse.class);
				
		return emailServiceResponse.getBody().getMessage();
	}
	
	
	/** -----------------------------------Service Endpoint for showing landing page (FOR NORMAL VISITOR)----------------------------------- */
	
	@GetMapping("/landingPage")
	public String landingPage(Model model) {
		
		// HTTP call to the backend service- book_catalog_service
		ResponseEntity<CatalogResponse> bookCatalogServiceResponse = restTemplate
				.getForEntity("http://book-catalog-service/showCatalog", CatalogResponse.class);

		List<Base64EncodedBook> featuredBooks = new ArrayList<Base64EncodedBook>();
		List<Base64EncodedBook> topSellerBooks = new ArrayList<Base64EncodedBook>();
		for (Book book : bookCatalogServiceResponse.getBody().getBooks()) {
			if (book.getSub_category().equals("Featured")) {
				featuredBooks.add(BooksBase64Encoder.getBase64Encoded(book));
			} else {
				topSellerBooks.add(BooksBase64Encoder.getBase64Encoded(book));
			}
		}
		model.addAttribute("featuredBooks", featuredBooks);
		model.addAttribute("topSellerBooks", topSellerBooks);
		return "landing";
	}
	
	
	/** -----------------------------------Service Endpoint for showing landing page (FOR CUSTOMER)----------------------------------- */
	
	@GetMapping("/customer/landingPage")
	public String customerlandingPage(Model model) {
		
		//HTTP call to the backend service- book_catalog_service
		ResponseEntity<CatalogResponse> bookCatalogServiceResponse = restTemplate.getForEntity("http://book-catalog-service/showCatalog", CatalogResponse.class);
		
		List<Base64EncodedBook> featuredBooks = new ArrayList<Base64EncodedBook>();
		List<Base64EncodedBook> topSellerBooks = new ArrayList<Base64EncodedBook>();
		for (Book book : bookCatalogServiceResponse.getBody().getBooks()) {
			if (book.getSub_category().equals("Featured")) {
				featuredBooks.add(BooksBase64Encoder.getBase64Encoded(book));
			} else {
				topSellerBooks.add(BooksBase64Encoder.getBase64Encoded(book));
			}
		}
		model.addAttribute("featuredBooks", featuredBooks);
		model.addAttribute("topSellerBooks", topSellerBooks);
		return "landing";
	}
	
	/** -----------------------------------Service Endpoint for showing shopping cart page (FOR CUSTOMER)----------------------------------- */
	
	@GetMapping("/customer/viewCart")
	public String customerCart() {
		
		return "customer-cart";
	}
	
	/** -----------------------------------Service Endpoint for showing particular book details----------------------------------- */
	
	@GetMapping("/viewBookDetails/{book_id}")
	public String viewBookDetails(@PathVariable Long book_id, Model model) {
		
		//HTTP call to the backend service- search_book_service
		ResponseEntity<SearchBookResponse> searchBookServiceResponse = restTemplate.getForEntity("http://search-book-service/searchBooksBookId/"+book_id, SearchBookResponse.class);
		model.addAttribute("searchedBookById", searchBookServiceResponse.getBody().getBooks().get(0));		
		
		return "";
	}
	
	
	/** -----------------------------------Service Endpoint for showing Search Page With Filtered Books (calls go to search-book-service)----------------------------------- */
	
	@GetMapping("/searchBooksByTitle/{title}")
	public String searchBookByTitle(@PathVariable String title, Model model) {
		
		//HTTP call to the backend service- search_book_service
		ResponseEntity<SearchBookResponse> searchBookServiceResponse = restTemplate.getForEntity("http://search-book-service/searchBooksTitle/"+title, SearchBookResponse.class);
		
		List<Base64EncodedBook> searchedBooks = new ArrayList<Base64EncodedBook>();
		for (SearchBook searchedBook  : searchBookServiceResponse.getBody().getBooks()) {
			searchedBooks.add(BooksBase64Encoder.getBase64Encoded(searchedBook));
		}
		
		model.addAttribute("searchedBooks", searchedBooks);		
		return "search";
	}
	
	@GetMapping("/searchBooksByISBN/{isbn}")
	public String searchBooksByISBN(@PathVariable String isbn, Model model) {
		
		//HTTP call to the backend service- search_book_service
		ResponseEntity<SearchBookResponse> searchBookServiceResponse = restTemplate.getForEntity("http://search-book-service/searchBooksISBN/"+isbn, SearchBookResponse.class);
		
		List<Base64EncodedBook> searchedBooks = new ArrayList<Base64EncodedBook>();
		for (SearchBook searchedBook  : searchBookServiceResponse.getBody().getBooks()) {
			searchedBooks.add(BooksBase64Encoder.getBase64Encoded(searchedBook));
		}
		
		model.addAttribute("searchedBooks", searchedBooks);		
		return "search";
	}
	
	@GetMapping("/searchBooksByAuthor/{author}")
	public String searchBooksByAuthor(@PathVariable String author, Model model) {
		
		//HTTP call to the backend service- search_book_service
		ResponseEntity<SearchBookResponse> searchBookServiceResponse = restTemplate.getForEntity("http://search-book-service/searchBooksAuthor/"+author, SearchBookResponse.class);
		
		List<Base64EncodedBook> searchedBooks = new ArrayList<Base64EncodedBook>();
		for (SearchBook searchedBook  : searchBookServiceResponse.getBody().getBooks()) {
			searchedBooks.add(BooksBase64Encoder.getBase64Encoded(searchedBook));
		}
		
		model.addAttribute("searchedBooks", searchedBooks);		
		return "search";
	}
	
	@GetMapping("/searchBooksByCategory/{category}")
	public String searchBooksByCategory(@PathVariable String category, Model model) {
		
		//HTTP call to the backend service- search_book_service
		ResponseEntity<SearchBookResponse> searchBookServiceResponse = restTemplate.getForEntity("http://search-book-service/searchBooksCategory/"+category, SearchBookResponse.class);
		
		List<Base64EncodedBook> searchedBooks = new ArrayList<Base64EncodedBook>();
		for (SearchBook searchedBook  : searchBookServiceResponse.getBody().getBooks()) {
			searchedBooks.add(BooksBase64Encoder.getBase64Encoded(searchedBook));
		}
		
		model.addAttribute("searchedBooks", searchedBooks);		
		return "search";
	}
	
	/** -----------------------------------Services for showing/managing Profile Settings and related services (calls go to profile_detail_service)----------------------------------- */
	
	@GetMapping("/customer/showSettingsPage")
	public String showSettingsPage(Principal principal, Model model) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse = restTemplate.exchange("http://profile-detail-service/getPersonalDetails",
																						HttpMethod.GET, entity, PersonalDetailsResponse.class);
		
		model.addAttribute("activeUserDetails", profileDetailsServiceResponse.getBody().getUserDetails());
		model.addAttribute("updateProfile", new UpdateProfileDetailsRequest());
		model.addAttribute("changePassword", new ChangePasswordRequest());
		return "customer-settings";
	}
	
	@PostMapping("/customer/updateProfileDetails")
	public String updateProfileDetails(@ModelAttribute("updateProfile") UpdateProfileDetailsRequest updateProfileDetailsRequest, Principal principal, Model model) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		HttpEntity<UpdateProfileDetailsRequest> entity = new HttpEntity<UpdateProfileDetailsRequest>(updateProfileDetailsRequest, headers);
		ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse1 = restTemplate.postForEntity("http://profile-detail-service/updatePersonalDetails", entity, PersonalDetailsResponse.class);
		
		sendEmail(principal.getName(), "PERSONAL_DETAIL_CHANGE_EMAIL");	
		return "redirect:/customer/showSettingsPage";
	}
	
	@GetMapping("/customer/togglePromotions/{toggleValue}")
	public String togglePromotions(Principal principal, Model model, @PathVariable String toggleValue) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse = 
				restTemplate.exchange("http://profile-detail-service/togglePromotionSubscription/" + toggleValue, HttpMethod.GET, entity, PersonalDetailsResponse.class);
		
		sendEmail(principal.getName(), "PROMOTION_SUBSCRIPTION_CHANGE_EMAIL");
		return "redirect:/customer/showSettingsPage";
	}
	
	@PostMapping("/customer/changePassword")
	public String changePassword(@ModelAttribute("changePassword") ChangePasswordRequest changePasswordRequest, Principal principal, Model model) {
				
		//Fetch old password
		ActiveUser activeUser = activeUserRepository.findByAccountId(principal.getName());
		
		//Check if old password matches
		if (bcryptPasswordEncoder.matches(changePasswordRequest.getOldPassword(), activeUser.getPassword())) {
			
			//Encode the new password
			changePasswordRequest.setNewPassword(bcryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("accountId", principal.getName());
			
			HttpEntity<ChangePasswordRequest> entity = new HttpEntity<ChangePasswordRequest>(changePasswordRequest, headers);
			ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse = restTemplate.postForEntity("http://profile-detail-service/changePassword", entity, PersonalDetailsResponse.class);
			
			sendEmail(principal.getName(), "PASSWORD_CHANGE_EMAIL");
			
			return "redirect:/customer/showSettingsPage";
		} else {
			model.addAttribute("changePasswordError", "Old password provided was incorrect");
			return "redirect:/customer/showSettingsPage";
		}
		
	}
	
	
	/** -----------------------------------Services for showing/managing Shipping Details and related services (calls go to shipping_detail_service)----------------------------------- */
	
	
	@GetMapping("/customer/getShippingDetails")
	public String getShippingDetails(Principal principal, Model theModel) {
		
		// Set account ID in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<ShippingInfoResponse> shippingDetailsServiceResponse = restTemplate.exchange("http://shipping-detail-service/getShippingDetails",
																						HttpMethod.GET, entity, ShippingInfoResponse.class);
		
		// Add information to model
		theModel.addAttribute("currentShippingInfo", shippingDetailsServiceResponse.getBody().getAddresses());
		theModel.addAttribute("updateShippingDetails", new ShippingInfoRequest());
		return "customer-addresses";
	}
	
	@PostMapping("/customer/processShippingForm")
	public String processShippingForm(Principal principal, Model theModel, @ModelAttribute("updateShippingDetails") ShippingInfoRequest shippingRequest) {
		
		// Set account id in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Set up Http entity with request body
		HttpEntity<ShippingInfoRequest> entity = new HttpEntity<ShippingInfoRequest>(shippingRequest, headers);
		
		// Send request to backend service
		ResponseEntity<ShippingInfoResponse> shippingDetailsServiceResponse = restTemplate.postForEntity("http://shipping-detail-service/updateShippingDetails", entity, ShippingInfoResponse.class);
		
		sendEmail(principal.getName(), "SHIPPING_DETAILS_UPDATE_EMAIL");
				
		return "redirect:/customer/getShippingDetails";
	}
	
	@PostMapping("/customer/deleteShippingDetails/{addressId}")
	public String deleteShippingDetails(Principal principal, @PathVariable long addressId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		DeleteShipppingDetailsRequest request = new DeleteShipppingDetailsRequest(addressId);
		HttpEntity<DeleteShipppingDetailsRequest> entity = new HttpEntity<DeleteShipppingDetailsRequest>(request, headers);
		
		ResponseEntity<ShippingInfoResponse> shippingDetailsServiceResponse = restTemplate.postForEntity("http://shipping-detail-service/deleteShippingDetails", entity, ShippingInfoResponse.class);
		
		sendEmail(principal.getName(), "SHIPPING_DETAILS_UPDATE_EMAIL");
		
		return "redirect:/customer/getShippingDetails";
	}
	
	
	/** -----------------------------------Services for showing/managing Payment Details and related services (calls go to payment_detail_service)----------------------------------- */
	
	
	@GetMapping("/customer/getPaymentDetails")
	public String getPaymentDetails(Principal principal, Model theModel) {
		
		// Set account ID in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<PaymentDetailsResponse> paymentDetailsServiceResponse = restTemplate.exchange("http://payment-detail-service/getPaymentDetails",
																						HttpMethod.GET, entity, PaymentDetailsResponse.class);
		
		// Add information to model
		theModel.addAttribute("userPaymentInfo", paymentDetailsServiceResponse.getBody().getCards());
		theModel.addAttribute("updatePaymentDetails", new UpdatePaymentDetailsRequest());
		theModel.addAttribute("addPaymentDetails", new AddPaymentDetailsRequest());
		return "customer-payments";
	}
	
	@PostMapping("/customer/addPaymentDetails")
	public String addPaymentDetails(Principal principal, Model theModel, @ModelAttribute("addPaymentDetails") AddPaymentDetailsRequest addPaymentDetailsRequest) {
		
		// Set account id in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Set up Http entity with request body
		HttpEntity<AddPaymentDetailsRequest> entity = new HttpEntity<AddPaymentDetailsRequest>(addPaymentDetailsRequest, headers);
		
		// Send request to backend service
		ResponseEntity<PaymentDetailsResponse> paymentDetailsServiceResponse = restTemplate.postForEntity("http://payment-detail-service/addPaymentDetails", entity, PaymentDetailsResponse.class);
		
		if (paymentDetailsServiceResponse.getStatusCode().equals(HttpStatus.OK) && paymentDetailsServiceResponse.getBody().getMessage().equals("Successs")) {
			sendEmail(principal.getName(), "PAYMENT_DETAILS_UPDATE_EMAIL");
			return "redirect:/customer/getPaymentDetails";
		} else {
			theModel.addAttribute("addPaymentError", paymentDetailsServiceResponse.getBody().getMessage());
			return "redirect:/customer/getPaymentDetails";
		}
	}
	
	@PostMapping("/customer/updatePaymentDetails")
	public String updatePaymentDetails(Principal principal, Model theModel, @ModelAttribute("updatePaymentDetails") UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
		
		// Set account id in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Set up Http entity with request body
		HttpEntity<UpdatePaymentDetailsRequest> entity = new HttpEntity<UpdatePaymentDetailsRequest>(updatePaymentDetailsRequest, headers);
		
		// Send request to backend service
		ResponseEntity<PaymentDetailsResponse> paymentDetailsServiceResponse = restTemplate.postForEntity("http://payment-detail-service/updatePaymentDetails", entity, PaymentDetailsResponse.class);
		
		if (paymentDetailsServiceResponse.getStatusCode().equals(HttpStatus.OK) && paymentDetailsServiceResponse.getBody().getMessage().equals("Successs")) {
			sendEmail(principal.getName(), "PAYMENT_DETAILS_UPDATE_EMAIL");
			return "redirect:/customer/getPaymentDetails";
		} else {
			theModel.addAttribute("updatePaymentError", paymentDetailsServiceResponse.getBody().getMessage());
			return "redirect:/customer/getPaymentDetails";
		}
	
	}
	
	@PostMapping("/customer/deletePaymentDetails/{cardNumber}")
	public String deletePaymentDetails(Principal principal, Model theModel, @PathVariable String cardNumber) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		DeletePaymentDetailsRequest deletePaymentDetailsRequest = new DeletePaymentDetailsRequest(cardNumber);
		HttpEntity<DeletePaymentDetailsRequest> entity = new HttpEntity<DeletePaymentDetailsRequest>(deletePaymentDetailsRequest, headers);
		
		ResponseEntity<PaymentDetailsResponse> paymentDetailsServiceResponse = restTemplate.postForEntity("http://payment-detail-service/deletePaymentDetails", entity, PaymentDetailsResponse.class);
		
		if (paymentDetailsServiceResponse.getStatusCode().equals(HttpStatus.OK) && paymentDetailsServiceResponse.getBody().getMessage().equals("Success")) {
			sendEmail(principal.getName(), "PAYMENT_DETAILS_UPDATE_EMAIL");
			return "redirect:/customer/getPaymentDetails";
		} else {
			theModel.addAttribute("deletePaymentError", paymentDetailsServiceResponse.getBody().getMessage());
			return "redirect:/customer/getPaymentDetails";
		}
	
	}
	
	
	/** -----------------------------------Services for showing/managing Cart Details and related services (calls go to cart_manage_service)----------------------------------- */
	
	
	@GetMapping("/customer/getCartDetails")
	public String getCartDetails (Principal principal, Model theModel) {

		// Set account ID in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service cart-manage-service
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<CartResponse> cartManageServiceResponse = restTemplate.exchange("http://cart-manage-service/getCartDetails",
																						HttpMethod.GET, entity, CartResponse.class);

		//Encode the books images to base64 string
		List<Base64EncodedBook> base64encodedCartBooks = new ArrayList<Base64EncodedBook>();
		
		if (cartManageServiceResponse.getBody().getBooks() != null) {
			for (CartBook cartBook : cartManageServiceResponse.getBody().getBooks()) {
				base64encodedCartBooks.add(BooksBase64Encoder.getBase64Encoded(cartBook));
			}
		}
		
		//Add information to the model
		theModel.addAttribute("cartBooks", base64encodedCartBooks);
		theModel.addAttribute("updateCartDetails", new CartUpdateRequest());
		return "customer-cart";
	}
	
	@PostMapping("/customer/addUpdateBookInCart")
	public String addUpdateBookToCart (Principal principal, Model theModel, @ModelAttribute("updateCartDetails") CartUpdateRequest cartUpdateRequest) {
		
		// Set account id in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Set up Http entity with request body
		HttpEntity<CartUpdateRequest> entity = new HttpEntity<CartUpdateRequest>(cartUpdateRequest, headers);
		
		// Send request to backend service
		ResponseEntity<CartResponse> cartManageServiceResponse = restTemplate.postForEntity("http://cart-manage-service/addUpdateBookToCart", entity, CartResponse.class);
		
		if (cartManageServiceResponse.getStatusCode().equals(HttpStatus.OK) && cartManageServiceResponse.getBody().getMessage().equals("Success")) {
			return "redirect:/customer/getCartDetails";
		} else {
			theModel.addAttribute("cartUpdateError", cartManageServiceResponse.getBody().getMessage());
			return "redirect:/customer/getCartDetails";
		}	
	}
	
	@GetMapping("/customer/removeBookFromCart/{bookId}")
	public String removeBookFromCart(Principal principal, Model theModel, @PathVariable Long bookId) {

		// Set account ID in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service cart-manage-service
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<CartResponse> cartManageServiceResponse = restTemplate.exchange("http://cart-manage-service/removeBook/"+bookId,
																						HttpMethod.GET, entity, CartResponse.class);
		
		if (cartManageServiceResponse.getStatusCode().equals(HttpStatus.OK) && cartManageServiceResponse.getBody().getMessage().equals("Success")) {
			return "redirect:/customer/getCartDetails";
		} else {
			theModel.addAttribute("bookDeleteError", cartManageServiceResponse.getBody().getMessage());
			return "redirect:/customer/getCartDetails";
		}
	}
	
	@GetMapping("/customer/deleteAllFromCart")
	public String deleteAllFromCart(Principal principal, Model theModel) {

		// Set account ID in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service cart-manage-service
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<CartResponse> cartManageServiceResponse = restTemplate.exchange("http://cart-manage-service/emptyCart",
																						HttpMethod.GET, entity, CartResponse.class);
		
		if (cartManageServiceResponse.getStatusCode().equals(HttpStatus.OK) && cartManageServiceResponse.getBody().getMessage().equals("Success")) {
			return "redirect:/customer/getCartDetails";
		} else {
			theModel.addAttribute("bookDeleteError", cartManageServiceResponse.getBody().getMessage());
			return "redirect:/customer/getCartDetails";
		}
	}
	
	
	/** --------------------Services for showing/Managing Checkout (calls three services: cart-manage-service, payment-detail-service, shipping-detail-service)----------------------------------- */
	
	@GetMapping("/customer/proceedToCheckout")
	public String proceedToCheckout(Principal principal, Model theModel) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
	
		// Make Request to backend service cart-manage-service
		HttpEntity<Object> entity1 = new HttpEntity<Object>(headers);
		ResponseEntity<CartResponse> cartManageServiceResponse = restTemplate.exchange("http://cart-manage-service/getCartDetails", HttpMethod.GET, entity1, CartResponse.class);
		
		// Encode the books images to base64 string
		List<Base64EncodedBook> base64encodedCartBooks = new ArrayList<Base64EncodedBook>();
		if (cartManageServiceResponse.getBody().getBooks() != null) {
			for (CartBook cartBook : cartManageServiceResponse.getBody().getBooks()) {
				base64encodedCartBooks.add(BooksBase64Encoder.getBase64Encoded(cartBook));
			}
		} else {
			theModel.addAttribute("getCartDetailsError", cartManageServiceResponse.getBody().getMessage());
		}
		
		// Make Request to backend service- shipping-detail-service
		HttpEntity<Object> entity2 = new HttpEntity<Object>(headers);
		ResponseEntity<ShippingInfoResponse> shippingDetailsServiceResponse = restTemplate.exchange("http://shipping-detail-service/getShippingDetails", HttpMethod.GET, entity2, ShippingInfoResponse.class);
		if (shippingDetailsServiceResponse.getBody().getMessage().equals("Failure") || shippingDetailsServiceResponse.getBody().getAddresses() == null) {
			theModel.addAttribute("getShippingDetailsError", shippingDetailsServiceResponse.getBody().getMessage());
		}
		
		// Make Request to backend service
		HttpEntity<Object> entity3 = new HttpEntity<Object>(headers);
		ResponseEntity<PaymentDetailsResponse> paymentDetailsServiceResponse = restTemplate.exchange("http://payment-detail-service/getPaymentDetails", HttpMethod.GET, entity3, PaymentDetailsResponse.class);
		if (paymentDetailsServiceResponse.getBody().getMessage().equals("Failure") || paymentDetailsServiceResponse.getBody().getCards() == null) {
			theModel.addAttribute("getPaymentDetailsError", paymentDetailsServiceResponse.getBody().getMessage());
		}
		
		
		// Since all cart, shipping, and payment details are fetched now return the Checkout Page View with appropriate Model object
		theModel.addAttribute("cartBooks", base64encodedCartBooks);
		theModel.addAttribute("currentShippingInfo", shippingDetailsServiceResponse.getBody().getAddresses());
		theModel.addAttribute("userPaymentInfo", paymentDetailsServiceResponse.getBody().getCards());
		
		//Calculate the total cost
		double totalCost = 0;
		for (Base64EncodedBook base64EncodedBook : base64encodedCartBooks) {
			totalCost = totalCost + base64EncodedBook.getPrice();
		}
		
		// Pas the empty model attribute object so that UI can populate order data in it to place the order
		theModel.addAttribute("placeOrderReq", new PlaceOrderRequest());
		theModel.addAttribute("totalCost", totalCost);
		return "customer-checkout";
				
	}
	
	/** -----------------------------------Services for Place Order related services (calls go to order_manage_service)----------------------------------- */
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
