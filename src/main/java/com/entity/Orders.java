package com.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Orders {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
    name = "order_products",
    joinColumns = @JoinColumn(name="order_id"),
    inverseJoinColumns = @JoinColumn(name="product_id")
    )
    
    private List<Product> products;

    private double totalPrice;

    private String shippingAddress;

    private String paymentStatus; // PENDING, SUCCESS, FAILED

    private LocalDateTime orderDate;
    
	public Orders() {
		super();
	}

	public Orders(Long id, User user, List<Product> products, double totalPrice, String shippingAddress,
			String paymentStatus, LocalDateTime orderDate) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
		this.totalPrice = totalPrice;
		this.shippingAddress = shippingAddress;
		this.paymentStatus = paymentStatus;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
}