package com.uga.forwords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditProfileController {
    @GetMapping("/showOrderHistory")
    public String showOrderHistory() {

        return "customer-orders";

    }

    @GetMapping("/showPayments")
    public String showPayments() {

        return "customer-payments";

    }

    @GetMapping("/showAddresses")
    public String showAddresses() {

        return "customer-addresses";

    }
}
