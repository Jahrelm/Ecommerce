package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.Cart;
import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.repository.CartRepository;
import com.example.ecommercemanagement.repository.ProductRepository;
import com.example.ecommercemanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository,UserRepository userRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Cart> list(int userId){
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isPresent()){
            return cartRepository.findAll();
        } else{
            throw new RuntimeException("User with ID " + userId + " not found");
        }

    }


    /*
    public Cart addToCart( long productId,int quantity, int userId){

        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        Optional<Product> productOptional = productRepository.findById(productId);
        Product product = productOptional.get();
        if(cartOptional.isPresent()){
            Cart cart = cartOptional.get();
            Iterable<Cart> userCartItems = cartRepository.findAll();
            for (Cart cartItem : userCartItems){
                if(cartItem.getProducts().equals(product)){
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);

                }else{
                    cartItem.getProducts().add(product);
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                    break;
                }
            }
            return cartRepository.save(cart);

        }else{
            Cart userCart = new Cart();
            userCart.setUserId(userId);
            userCart.setProducts(new ArrayList<>(Collections.singletonList(product)));
            userCart.setQuantity(quantity);
            return cartRepository.save(userCart);

        }
    }
    */
    public Cart addToCart(long productId, int quantity, int userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            if (cartOptional.isPresent()) {
                Cart cart = cartOptional.get();
                boolean productExistsInCart = false;
                for (Product p : cart.getProducts()) {
                    if (p.getProductId().equals(productId)) {
                        cart.setQuantity(cart.getQuantity() + quantity);
                        productExistsInCart = true;
                        break;
                    }
                }
                if (!productExistsInCart) {
                    cart.getProducts().add(product);
                    cart.setQuantity(cart.getQuantity() + quantity);
                }
                return cartRepository.save(cart);
            } else {
                Cart userCart = new Cart();
                userCart.setUserId(userId);
                userCart.setProducts(new ArrayList<>(Collections.singletonList(product)));
                userCart.setQuantity(quantity);
                return cartRepository.save(userCart);
            }
        }else{
            throw new RuntimeException("Product with ID " + productId + " not found");
        }

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
/*
    public Set<Cart> getCartsForUser(Integer userId) {
        ApplicationUser user = userRepository.findById(userId).orElse(null);
        return user != null ? user.getCarts() : null;
    }
    */

}