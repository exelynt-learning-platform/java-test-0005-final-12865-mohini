package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.entity.Cart;
import com.service.CartService;

import dto.CartDTO;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public Cart addToCart(@RequestBody CartDTO dto) throws Exception {
        return cartService.addToCart(dto.getUserId(), dto.getProductId(), dto.getQuantity());
    }

    @GetMapping("/{userId}")
    public List<Cart> getUserCart(@PathVariable Long userId) throws Exception {
        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/{cartId}")
    public String removeFromCart(@PathVariable Long cartId) {
        return cartService.removeFromCart(cartId);
    }
}