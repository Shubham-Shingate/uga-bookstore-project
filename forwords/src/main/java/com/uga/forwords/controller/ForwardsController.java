package com.uga.forwords.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import com.uga.forwords.model.Base64EncodedBook;
import com.uga.forwords.model.Book;
import com.uga.forwords.model.SearchBook;
import com.uga.forwords.request.ChangePasswordRequest;
import com.uga.forwords.request.UpdateProfileDetailsRequest;
import com.uga.forwords.response.CatalogResponse;
import com.uga.forwords.response.PersonalDetailsResponse;
import com.uga.forwords.response.SearchBookResponse;
import com.uga.forwords.util.BooksBase64Encoder;

@Controller
public class ForwardsController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@GetMapping("/home")
	public String showHome() {
		
		return "home";
	}
	
	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	
	/** -----------------------------------Service Endpoint for showing landing page----------------------------------- */
	
	@GetMapping("/landingPage")
	public String landingPage(Model model) {
		
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
	
	/** -----------------------------------Service Endpoint for showing particular book details----------------------------------- */
	
	@GetMapping("/viewBookDetails/{book_id}")
	public String viewBookDetails(@PathVariable Long book_id, Model model) {
		
		//HTTP call to the backend service- search_book_service
		ResponseEntity<SearchBookResponse> searchBookServiceResponse = restTemplate.getForEntity("http://search-book-service/searchBooksBookId/"+book_id, SearchBookResponse.class);
		model.addAttribute("searchedBookById", searchBookServiceResponse.getBody().getBooks().get(0));		
		
		return "";
	}
	
	
	/** -----------------------------------Service Endpoint for showing Search Page With Filtered Books----------------------------------- */
	
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
	
	/** -----------------------------------Service Endpoint for showing Edit Profile Pages----------------------------------- */
	
	@GetMapping("/showSettingsPage")
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
	
	
	@PostMapping("/updateProfileDetails")
	public String updateProfileDetails(Principal principal, Model model, @Valid @ModelAttribute("updateProfile") UpdateProfileDetailsRequest updateProfileDetailsRequest) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		HttpEntity<UpdateProfileDetailsRequest> entity = new HttpEntity<UpdateProfileDetailsRequest>(updateProfileDetailsRequest, headers);
		ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse1 = restTemplate.postForEntity("http://profile-detail-service/updatePersonalDetails", entity, PersonalDetailsResponse.class);
		
//		HttpEntity<Object> entity2 = new HttpEntity<Object>(headers);
//		ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse2 = restTemplate.exchange("http://profile-detail-service/getPersonalDetails",
//																						HttpMethod.GET, entity2, PersonalDetailsResponse.class);
//		
//		model.addAttribute("activeUserDetails", profileDetailsServiceResponse2.getBody().getUserDetails());
//		return "customer-settings";
		
		return "redirect:/showSettingsPage";
		
	}
	
	
	@GetMapping("/togglePromotions/{toggleValue}")
	public String togglePromotions(Principal principal, Model model, @PathVariable String toggleValue) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse = 
				restTemplate.exchange("http://profile-detail-service/togglePromotionSubscription/" + toggleValue, HttpMethod.GET, entity, PersonalDetailsResponse.class);

		return "redirect:/showSettingsPage";
	}
	
	
	
	@PostMapping("/changePassword")
	public String changePassword(Principal principal, Model model, @Valid @ModelAttribute("changePassword") ChangePasswordRequest changePasswordRequest) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("accountId", principal.getName());
		
		//Encode the old password
		String encryptedOldPassword = bcryptPasswordEncoder.encode(changePasswordRequest.getOldPassword());
		changePasswordRequest.setOldPassword(encryptedOldPassword);
		
		HttpEntity<ChangePasswordRequest> entity = new HttpEntity<ChangePasswordRequest>(changePasswordRequest, headers);
		ResponseEntity<PersonalDetailsResponse> profileDetailsServiceResponse = restTemplate.postForEntity("http://profile-detail-service/changePassword", entity, PersonalDetailsResponse.class);
		
		
		return "redirect:/showSettingsPage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
