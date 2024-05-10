package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.*;
import com.example.ecommercemanagement.repository.CartRepository;
import com.example.ecommercemanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Iterable<Cart> list(){
        return cartRepository.findAll();
    }

    public Cart addToCart(Long productId, int quantity){
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null){
            return null;
        }
        Cart cartItem = new Cart();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cartItem.setTitle(product.getTitle());
        cartItem.setBrand(product.getBrand());
        cartItem.setPrice(product.getPrice());
        double totalCost = calculateTotalCost(product.getPrice(), quantity);
        cartItem.setTotalCost(totalCost);
        double cartCost = calculateCartCost();
        cartItem.setCartCost(cartCost);
        return cartRepository.save(cartItem);

    }
    public void removeFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }
    private double calculateTotalCost(String price, int quantity){
        double productPrice = Double.parseDouble(price.replace("$", ""));
        return productPrice * quantity;
    }
    private double calculateCartCost(){
        double cartCost = 0.0;
        Iterable<Cart> cartItems = list();
        for(Cart cartItem : cartItems){
            cartCost += cartItem.getTotalCost();
        }
        return cartCost;
    }
}
