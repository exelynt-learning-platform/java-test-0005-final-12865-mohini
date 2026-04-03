package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Cart;
import com.entity.Orders;
import com.entity.Product;
import com.entity.User;
import com.repository.CartRepository;
import com.repository.OrderRepository;
import com.repository.ProductRepository;
import com.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Orders createOrder(Long userId, String shippingAddress) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        List<Cart> cartItems = cartRepository.findAllByUser(user);

        if(cartItems.isEmpty()) throw new Exception("Cart is empty");

        double total = cartItems.stream().mapToDouble(c -> c.getProduct().getPrice() * c.getQuantity()).sum();
        List<Product> products = cartItems.stream().map(Cart::getProduct).toList();

        Orders order = new Orders();
        order.setUser(user);
        order.setProducts(products);
        order.setTotalPrice(total);
        order.setShippingAddress(shippingAddress);
        order.setPaymentStatus("PENDING");

        orderRepository.save(order);
        cartRepository.deleteAll(cartItems); // Clear cart
        return order;
    }

    public String payWithStripe(String stripeApiKey, double amount) throws StripeException {

        Stripe.apiKey = stripeApiKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long)(amount * 100))
                .setCurrency("usd")
                .addPaymentMethodType("card") // ✅ FIX
                .build();

        PaymentIntent intent = PaymentIntent.create(params);

        return intent.getClientSecret();
    }
}