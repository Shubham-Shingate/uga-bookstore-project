package com.uga.forwords.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentsController {
    @GetMapping("/showPayments")
    public String showPayments() {

        return "customer-payments";

    }
}
