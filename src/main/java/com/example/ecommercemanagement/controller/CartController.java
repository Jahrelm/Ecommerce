package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.Cart;
import com.example.ecommercemanagement.service.AuthenticationService;
import com.example.ecommercemanagement.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:3000")
public class CartController {

        private AuthenticationService authenticationService;

        private CartService cartService;

        public CartController(CartService cartService) {
            this.cartService = cartService;
        }

        @GetMapping("/list")
        @PreAuthorize("hasRole('USER')")
        public Iterable<Cart> list(){
            return cartService.list();
        }

        @PostMapping("/add")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<Cart> addToCart(@RequestParam Long productId, @RequestParam int quantity) {
            Cart cartItem = cartService.addToCart(productId, quantity);
            if (cartItem != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> removeFromCart(@RequestParam Long cartItemId){
        cartService.removeFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/removeAll")
    @PreAuthorize("hasRole('USER')")
    public  ResponseEntity<Cart> removeAllFromCart(){
            try{
                cartService.removeAllFromCart();;
                return ResponseEntity.ok().build();
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

            }
    }

}

