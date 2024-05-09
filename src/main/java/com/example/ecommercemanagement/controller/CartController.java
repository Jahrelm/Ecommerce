package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.Cart;
import com.example.ecommercemanagement.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
        private CartService cartService;

        public CartController(CartService cartService) {
            this.cartService = cartService;
        }
        @PostMapping("/add")
        public ResponseEntity<Cart> addToCart(@RequestParam Long productId, RequestParam int quantity){
        Cart cartItem = cartService.addToCart(productId, quantity);
        if (cartItem != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
        }else {
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/remove/{id}")
        public ResponseEntity<Void> removeFromCart(@PathVariable Long id){
          cartService.removeFromCart(id);
          return ResponseEntity.noContent.build();

        }
    }

