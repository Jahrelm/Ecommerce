package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.Cart;
import com.example.ecommercemanagement.model.CartItem;
import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.repository.CartItemRepository;
import com.example.ecommercemanagement.repository.CartRepository;
import com.example.ecommercemanagement.repository.ProductRepository;
import com.example.ecommercemanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository,UserRepository userRepository, CartItemRepository cartItemRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<Cart> findCartsByProductId(Long productId) {
        List<CartItem> cartItems = cartItemRepository.findByProductId(productId);
        return cartItems.stream()
                .map(CartItem::getCart)
                .distinct()
                .collect(Collectors.toList());
    }

    public Iterable<Cart> list(int userId) {
        ApplicationUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));
        Optional<Cart> cartOptional = cartRepository.findByUser(user);
        return cartOptional.map(Collections::singletonList).orElse(Collections.emptyList());
    }


    public Cart addToCart(long productId, int quantity, int userId) {
        logger.info("Entering addToCart with ProductId: {}, Quantity: {}, UserId: {}", productId, quantity, userId);

        ApplicationUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }

        Product product = productOptional.get();
        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setTotalCost(0.0);
            newCart.setCartItems(new ArrayList<>());
            return cartRepository.save(newCart);
        });


        Optional<CartItem> cartItemOptional = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        CartItem cartItem;
        if (cartItemOptional.isPresent()) {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }

        cartItem.calculateSubTotal();
        double newTotalCost = cart.getCartItems().stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
        cart.setTotalCost(newTotalCost);

        return cartRepository.save(cart);
    }

    public Cart removeFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found with ID " + cartItemId));

        Cart cart = cartItem.getCart();
        cartItemRepository.deleteById(cartItemId);
        cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));
        cart.setTotalCost(cart.getCartItems().stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum());
        return cartRepository.save(cart);
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