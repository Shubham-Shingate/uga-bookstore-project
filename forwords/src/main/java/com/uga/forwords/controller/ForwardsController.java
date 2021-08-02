package com.uga.forwords.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.uga.forwords.model.ActiveUser;
import com.uga.forwords.model.Base64EncodedBook;
import com.uga.forwords.model.Book;
import com.uga.forwords.model.BookEntry;
import com.uga.forwords.model.CartBook;
import com.uga.forwords.model.Config;
import com.uga.forwords.model.SearchBook;
import com.uga.forwords.request.AddPaymentDetailsRequest;
import com.uga.forwords.request.CartUpdateRequest;
import com.uga.forwords.request.ChangePasswordRequest;
import com.uga.forwords.request.DeletePaymentDetailsRequest;
import com.uga.forwords.request.DeleteShipppingDetailsRequest;
import com.uga.forwords.request.EmailRequest;
import com.uga.forwords.request.OrderRequest;
import com.uga.forwords.request.PromotionInfoRequest;
import com.uga.forwords.request.ShippingInfoRequest;
import com.uga.forwords.request.UpdatePaymentDetailsRequest;
import com.uga.forwords.request.UpdateProfileDetailsRequest;
import com.uga.forwords.response.CartResponse;
import com.uga.forwords.response.CatalogResponse;
import com.uga.forwords.response.EmailResponse;
import com.uga.forwords.response.OrderResponse;
import com.uga.forwords.response.PaymentDetailsResponse;
import com.uga.forwords.response.PersonalDetailsResponse;
import com.uga.forwords.response.PromotionInfoResponse;
import com.uga.forwords.response.SearchBookResponse;
import com.uga.forwords.response.ShippingInfoResponse;
import com.uga.forwords.service.ActiveUserRepository;
import com.uga.forwords.service.BookRepository;
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
		model.addAttribute("updateCartDetails", new CartUpdateRequest());
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
		model.addAttribute("updateCartDetails", new CartUpdateRequest());
		
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
		
		model.addAttribute("searchedBookById", BooksBase64Encoder.getBase64Encoded(searchBookServiceResponse.getBody().getBooks().get(0)));
		model.addAttribute("updateCartDetails", new CartUpdateRequest());
		
		return "book-details";
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
		
		if (paymentDetailsServiceResponse.getStatusCode().equals(HttpStatus.OK) && paymentDetailsServiceResponse.getBody().getMessage().equals("Success")) {
			sendEmail(principal.getName(), "PAYMENT_DETAILS_UPDATE_EMAIL");
			return "redirect:/customer/getPaymentDetails";
		} else {
			theModel.addAttribute("addPaymentError", ((LinkedHashMap<?, ?>) paymentDetailsServiceResponse.getBody().getApiError()).get("message"));
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
		
		if (paymentDetailsServiceResponse.getStatusCode().equals(HttpStatus.OK) && paymentDetailsServiceResponse.getBody().getMessage().equals("Success")) {
			sendEmail(principal.getName(), "PAYMENT_DETAILS_UPDATE_EMAIL");
			return "redirect:/customer/getPaymentDetails";
		} else {
			theModel.addAttribute("updatePaymentError", ((LinkedHashMap<?, ?>) paymentDetailsServiceResponse.getBody().getApiError()).get("message"));
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
			theModel.addAttribute("deletePaymentError", ((LinkedHashMap<?, ?>) paymentDetailsServiceResponse.getBody().getApiError()).get("message"));
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
		
		// Send request to backend service: cart-manage-service
		ResponseEntity<CartResponse> cartManageServiceResponse = restTemplate.postForEntity("http://cart-manage-service/addUpdateBookToCart", entity, CartResponse.class);
		
		if (cartManageServiceResponse.getStatusCode().equals(HttpStatus.OK) && cartManageServiceResponse.getBody().getMessage().equals("Success")) {
			return "redirect:/customer/getCartDetails";
		} else {
			theModel.addAttribute("cartUpdateError", ((LinkedHashMap<?, ?>) cartManageServiceResponse.getBody().getApiError()).get("message"));
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
			theModel.addAttribute("bookDeleteError", ((LinkedHashMap<?, ?>) cartManageServiceResponse.getBody().getApiError()).get("message"));
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
			theModel.addAttribute("bookDeleteError", ((LinkedHashMap<?, ?>) cartManageServiceResponse.getBody().getApiError()).get("message"));
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
			theModel.addAttribute("getCartDetailsError", ((LinkedHashMap<?, ?>) cartManageServiceResponse.getBody().getApiError()).get("message"));
		}
		
		// Make Request to backend service- shipping-detail-service
		HttpEntity<Object> entity2 = new HttpEntity<Object>(headers);
		ResponseEntity<ShippingInfoResponse> shippingDetailsServiceResponse = restTemplate.exchange("http://shipping-detail-service/getShippingDetails", HttpMethod.GET, entity2, ShippingInfoResponse.class);
		if (shippingDetailsServiceResponse.getBody().getMessage().equals("Failure") || shippingDetailsServiceResponse.getBody().getAddresses() == null) {
			theModel.addAttribute("getShippingDetailsError", ((LinkedHashMap<?, ?>) shippingDetailsServiceResponse.getBody().getApiError()).get("message"));
		}
		
		// Make Request to backend service
		HttpEntity<Object> entity3 = new HttpEntity<Object>(headers);
		ResponseEntity<PaymentDetailsResponse> paymentDetailsServiceResponse = restTemplate.exchange("http://payment-detail-service/getPaymentDetails", HttpMethod.GET, entity3, PaymentDetailsResponse.class);
		if (paymentDetailsServiceResponse.getBody().getMessage().equals("Failure") || paymentDetailsServiceResponse.getBody().getCards() == null) {
			theModel.addAttribute("getPaymentDetailsError", ((LinkedHashMap<?, ?>) paymentDetailsServiceResponse.getBody().getApiError()).get("message"));
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
		List<BookEntry> books = new ArrayList<BookEntry>();
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setBooks(books);
		
		theModel.addAttribute("orderRequest", orderRequest);
		theModel.addAttribute("totalCost", totalCost);
		return "customer-checkout";
				
	}
	
	/** -----------------------------------Services for Place Order related services (calls go to order_manage_service)----------------------------------- */
		
	
	@PostMapping("/customer/placeOrder")
	public String placeOrder(Principal principal, Model theModel, @ModelAttribute("orderRequest") OrderRequest orderRequest) {
		
		// Set account id in header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		HttpEntity<OrderRequest> entity = new HttpEntity<OrderRequest>(orderRequest, headers);
		
		// Send request to backend service: cart-manage-service
		ResponseEntity<OrderResponse> orderManageServiceResponse = restTemplate.postForEntity("http://order-manage-service/placeOrder", entity, OrderResponse.class);
		
		if (orderManageServiceResponse.getStatusCode().equals(HttpStatus.OK) && orderManageServiceResponse.getBody().getMessage().equals("Success")) {
			//Send confirmation email for order placed
			sendEmail(principal.getName(), "ORDER_PLACED_CONFIRMATION_EMAIL");
			return "customer-order-confirmation";
		} else {
			theModel.addAttribute("orderPlacementError", ((LinkedHashMap<?, ?>) orderManageServiceResponse.getBody().getApiError()).get("message"));
			return "redirect:/customer/proceedToCheckout";
		}
		
	}
	
	@GetMapping("/customer/getOrderHistory")
	public String getOrderHistory (Principal principal, Model theModel) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service cart-manage-service
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<OrderResponse> orderManageServiceResponse = restTemplate.exchange("http://order-manage-service/getOrderHistory", HttpMethod.GET, entity, OrderResponse.class);	
		
		if (orderManageServiceResponse.getStatusCode().equals(HttpStatus.OK) && orderManageServiceResponse.getBody().getMessage().equals("Success")) {
			
			theModel.addAttribute("pastOrders", orderManageServiceResponse.getBody().getOrders());
			return "customer-order-history";
		} else {
			theModel.addAttribute("orderHistoryError", ((LinkedHashMap<?, ?>) orderManageServiceResponse.getBody().getApiError()).get("message"));
			return "customer-order-history";
		}
		
	}
	
	@GetMapping("/customer/fetchOrder/{orderId}")
	public String fetchOrder(Principal principal, Model theModel, @PathVariable String orderId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		// Make Request to backend service cart-manage-service
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<OrderResponse> orderManageServiceResponse = restTemplate.exchange("http://order-manage-service/fetchOrder/"+orderId, HttpMethod.GET, entity, OrderResponse.class);	
		
		if (orderManageServiceResponse.getStatusCode().equals(HttpStatus.OK)
				&& orderManageServiceResponse.getBody().getMessage().equals("Success")) {

			theModel.addAttribute("orderDetails", orderManageServiceResponse.getBody().getOrders().get(0));
			return "customer-order";
		} else {
			theModel.addAttribute("orderHistoryError", ((LinkedHashMap<?, ?>) orderManageServiceResponse.getBody().getApiError()).get("message"));
			return "redirect:/customer/getOrderHistory";
		}
		
	}
	
	/** -----------------------------------Manage Promotions Related Services (calls go to promotion_manage_service)----------------------------------- */
	
	@GetMapping("/admin/viewPromotions")
	public String viewPromotions(Model model) {
		
		// Obtain promotions list
		ResponseEntity<PromotionInfoResponse> allPromotions = restTemplate.getForEntity("http://promotion-manage-service/fetchAllPromotions", PromotionInfoResponse.class);
		
		if (allPromotions.getStatusCode().equals(HttpStatus.OK) && allPromotions.getBody().getMessage().equals("Success")) {
			// Add list to model
			model.addAttribute("promotionsList", allPromotions.getBody().getPromotions());
			return "admin-manage-promotions";
		} else {
			model.addAttribute("viewPromosError", ((LinkedHashMap<?, ?>) allPromotions.getBody().getApiError()).get("message"));
			return "admin-manage-promotions";
		}
		
	}
	
	@GetMapping("/admin/showAddPromoPage")
	public String showAddPromoPage(Model model) {
		model.addAttribute("addPromotionRequest", new PromotionInfoRequest());
		
		return "admin-add-promotion"; 
	}
		
	@PostMapping("/admin/processPromotionAddition")
	public String processPromotionAddition(Model model, @ModelAttribute("addPromotionRequest") PromotionInfoRequest promotionRequest) {
		
		// Set up Http entity with request body
		HttpEntity<PromotionInfoRequest> entity = new HttpEntity<PromotionInfoRequest>(promotionRequest);
		
		// Send request to backend service
		ResponseEntity<PromotionInfoResponse> promotionServiceResponse = restTemplate.postForEntity("http://promotion-manage-service/createPromotion", entity, PromotionInfoResponse.class);
		
		if (promotionServiceResponse.getStatusCode().equals(HttpStatus.OK) && promotionServiceResponse.getBody().getMessage().equals("Success")) {
			return "redirect:/admin/viewPromotions";
		} else {
			model.addAttribute("addPromosError", ((LinkedHashMap<?, ?>) promotionServiceResponse.getBody().getApiError()).get("message"));
			return "redirect:/admin/viewPromotions";
		}
		
	}
	
	/** ------------------------------------------------- Book Management --------------------------------------------------------------*/
	@GetMapping("/admin/viewBookInventory")
	public String viewBookInventory(Model model) {
		
		// Fetch book list using book_catalog_service (don't need images)
		ResponseEntity<CatalogResponse> catalogResponse = restTemplate.getForEntity("http://book-catalog-service/showCatalog", CatalogResponse.class);
		
		List<Base64EncodedBook> inventory = new ArrayList<Base64EncodedBook>();
		
		// Initializes inventory list
		for(Book book : catalogResponse.getBody().getBooks()) {
			inventory.add(BooksBase64Encoder.getBase64Encoded(book));
		}
		
		model.addAttribute("bookInventory", inventory);
		
		return "admin-dashboard";	
	}
	
	@GetMapping("/admin/showAddBookPage")
	public String showAddBookPage(Model model) {
		return "admin-add-book";
	}
	
//	@GetMapping(value = "/admin/addBook", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//	public String addBook(MultipartHttpServletRequest request) {
//			
//		// If encountering issues, try XMLHttpRequest and/or constructing a FormData object in the frontend
//		
//		// Attempt with RequestDispatcher
////		RequestDispatcher dispatcher = request.getServletContext().getContext("http://book-manage-service/updateBook").getRequestDispatcher("http://book-manage-service/updateBook");
//		
//		// Forward request to backend service
//		ResponseEntity<BookResponse> bookResponse = restTemplate.postForEntity("http://book-manage-service/updateBook", request, BookResponse.class);
//		
//		return "redirect:/admin-dashboard";
//	}
	
	@Autowired BookRepository bookRepository;
	
	@PostMapping(value = "/admin/updateBook", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )
	public String updateBook(
			@RequestParam String title,
			@RequestParam String isbn,
			@RequestParam String author,
			@RequestParam String category,
			@RequestParam String description,
			@RequestPart(value="coverPicture") MultipartFile coverPicture,
			@RequestParam Long publicationYear,
			@RequestParam String edition,
			@RequestParam String publisher,
			@RequestParam Long quantity,
			@RequestParam Long minThreshold,
			@RequestParam Double price
			) throws IOException, Exception{
		
		/* Cannot get the above validations to work */
		// Check validity of parameters
		if(title == null || isbn == null || author == null || category == null || publicationYear == null || description == null || 
				coverPicture == null || publisher == null || quantity == null || minThreshold == null
				|| price == null /* || subCategory == null */)
			throw new Exception("No values can be null. Some String values can be empty, but never null.");
		
		if(title.isEmpty() || isbn.isEmpty() || author.isEmpty() || category.isEmpty() || publisher.isEmpty() || price.toString().isEmpty() 
				|| quantity.toString().isEmpty() || minThreshold.toString().isEmpty() || publicationYear.toString().isEmpty() || coverPicture.isEmpty())
			throw new Exception("One or more mandatory fields is empty. Review your submission and make sure no required values are left blank.");
		
		if(quantity < minThreshold) {
			throw new Exception("Quantity cannot be less than the minimum threshold");
		}
		
		// Calculate status
		String book_status;
		if(quantity > 0)
			book_status = "AVAILABLE";
		else if(quantity < 0) {
			quantity = 0L;
			book_status = "UNAVAILABLE";
		}
		else
			book_status = "UNAVAILABLE";
		
		// Convert image to byte[]
		byte[] img;
		try {
			img = coverPicture.getBytes();
		}
		catch(IOException e) {
			throw new Exception("Problem encountered while converting image to bit[] for database storage");
		}
		
		// Convert byte[] to Byte[]
		Byte[] convertedImg = new Byte[img.length];
		for(int i = 0; i < img.length; i++)
			convertedImg[i] = img[i];
		
		Book newBook = new Book(
				title, 
				isbn, 
				author,
				category, 
				description, 
				convertedImg,
				publicationYear, 
				edition, 
				publisher,
				book_status, 
				quantity, 
				minThreshold, 
				price,
				""
				);
		
		// Add to DB
		bookRepository.save(newBook);
		
		// Return response
		return "redirect:/admin/viewBookInventory";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
