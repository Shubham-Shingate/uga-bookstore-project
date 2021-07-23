package com.uga.forwords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardsController {
	
	
	
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
