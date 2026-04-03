package com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;

@Service
public class PaymentService {
    public String pay(Double amount) throws Exception {
        Stripe.apiKey = "your_key";

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int)(amount * 100));
        params.put("currency", "inr");

        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getId();
    }

	public Object createPayment(Double amount) {
		// TODO Auto-generated method stub
		return null;
	}
}

