package com.example.ecommercemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.Collection;


@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JoinColumn(name = "user_id")
    @Column(name="user_id")
    private Integer userId;

    @JoinColumn(name = "product_id")
    @Column(name="product_id")
    private Long productId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @Valid
    private Collection<Product> Products;

    private int quantity;
    public double totalCost;

    public double cartCost;

    public Cart() {
        // Default constructor
    }

    public Cart(Long id, Integer userId, Long productId, Collection<Product> products, int quantity, double totalCost, double cartCost) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        Products = products;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.cartCost = cartCost;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Collection<Product> getProducts() {
        return Products;
    }

    public void setProducts(Collection<Product> products) {
        Products = products;
    }

    public double getCartCost() {
        return cartCost;
    }

    public void setCartCost(double cartCost) {
        this.cartCost = cartCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}
