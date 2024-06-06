package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.Cart;
import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.repository.CartRepository;
import com.example.ecommercemanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Iterable<Cart> list(){
        return cartRepository.findAll();
    }

    public Cart addToCart(@RequestParam Long productId, @RequestParam int quantity){
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()){
            return null;
        }
        Product product = productOptional.get();
        Optional<Cart> cartItemOptional = cartRepository.findByProductId(productId);

        Cart cartItem;
        if (cartItemOptional.isPresent()) {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new Cart();
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cartItem.setTitle(product.getTitle());
            cartItem.setBrand(product.getBrand());
            cartItem.setPrice(product.getPrice());
        }

        double totalCost = calculateTotalCost(product.getPrice(), cartItem.getQuantity());
        cartItem.setTotalCost(totalCost);

        cartItem = cartRepository.save(cartItem);

        double cartCost = calculateCartCost();
        cartItem.setCartCost(cartCost);

        return cartRepository.save(cartItem);
    }

    public void removeFromCart(@RequestParam Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    public void removeAllFromCart(){
        cartRepository.deleteAll();
    }

    private double calculateTotalCost(String price, int quantity){
        double productPrice = Double.parseDouble(price.replace("$", ""));
        return productPrice * quantity;
    }
    private double calculateCartCost() {
        Iterable<Cart> cartItems = cartRepository.findAll();
        double cartCost = 0.0;
        for (Cart cartItem : cartItems) {
            cartCost += cartItem.getTotalCost();
        }
        return cartCost;
    }
}