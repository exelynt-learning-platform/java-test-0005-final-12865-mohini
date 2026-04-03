package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.entity.Orders;
import com.service.OrderService;
import com.stripe.exception.StripeException;

import dto.OrderDTO;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Orders createOrder(@RequestBody OrderDTO dto) throws Exception {
        return orderService.createOrder(dto.getUserId(), dto.getShippingAddress());
    }

    @PostMapping("/pay")
    public String payOrder(@RequestParam double amount) throws StripeException {
        // Use your Stripe secret key from application.properties
        return orderService.payWithStripe("sk_test_51N3...", amount);
    }
}