package com.uga.forwords.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardsController {
	
	//private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
	
//	@GetMapping("/")
//	public String landingPage(Model model) {
//
//		model.addAttribute("message", "Cole");
//        model.addAttribute("tasks", tasks);
//
//        return "welcome";
//	}
//	
//	
//	@GetMapping("/myAccountDetails")
//	public String myAccountDetails () {
//		
//		
//		
//		return "";
//	}
	
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
	
	
	
}
