package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.repository.CartRepository;
import com.repository.ProductRepository;
import com.repository.UserRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addToCart(Long userId, Long productId, int qty) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found"));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(qty);

        return cartRepository.save(cart);
    }

    public List<Cart> getUserCart(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        return cartRepository.findAllByUser(user);
    }

    public String removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
        return "Item removed from cart";
    }
}