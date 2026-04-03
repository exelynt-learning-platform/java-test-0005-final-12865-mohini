package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/pay")
    public String pay() throws StripeException {

        Stripe.apiKey = "your_secret_key";

        Map<String, Object> params = new HashMap<>();
        params.put("amount", 1000);
        params.put("currency", "inr");

        PaymentIntent intent = PaymentIntent.create(params);

        return intent.getId();
    }
}