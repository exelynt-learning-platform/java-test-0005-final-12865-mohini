package com.controller;

import com.entity.Product;
import com.service.ProductService;

import dto.ProductDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());

        return service.addProduct(product); // ✅ FIX
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts(); // ✅ FIX
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) throws Exception {
        return service.getProduct(id); // ✅ FIX
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updated) throws Exception {
        return service.updateProduct(id, updated); // ✅ FIX
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id); // ✅ FIX
    }
}