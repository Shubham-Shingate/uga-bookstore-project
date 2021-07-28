package com.uga.forwords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/showAdminDashboard")
	public String showAdminDashboard() {

		return "admin-dashboard";

	}

	@GetMapping("/addBookPage")
	public String addBookPage() {

		return "admin-add-book";

	}

	@GetMapping("/showManagePromo")
	public String showManagePromo() {

		return "admin-manage-promotions";

	}

	@GetMapping("/addPromoPage")
	public String addPromoPage() {

		return "admin-add-promotion";

	}

}
