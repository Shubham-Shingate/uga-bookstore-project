package com.uga.forwords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShippingController {
    @GetMapping("/showAddresses")
    public String showAddresses() {

        return "customer-addresses";

    }
}
