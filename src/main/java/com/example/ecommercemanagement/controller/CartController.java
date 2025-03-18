package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.Cart;
import com.example.ecommercemanagement.service.AuthenticationService;
import com.example.ecommercemanagement.service.CartService;
import com.example.ecommercemanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:3000")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private AuthenticationService authenticationService;

    private CartService cartService;

    @Autowired
    private UserService userService;

    public CartController(CartService cartService, UserService userService)
    {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/by-product/{productId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Cart>> getCartsByProductId(@PathVariable Long productId) {
        try {
            List<Cart> carts = cartService.findCartsByProductId(productId);
            return ResponseEntity.ok(carts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Iterable<Cart>> list(@PathVariable int userId) {
        try {
            Iterable<Cart> carts = cartService.list(userId);
            return ResponseEntity.ok(carts);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> addToCart(@PathVariable int userId, 
                                        @RequestParam Long productId, 
                                        @RequestParam int quantity) {
        try {
            ApplicationUser user = userService.FindUserById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            
            Cart cartItem = cartService.addToCart(productId, quantity, userId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cartItem);
        } catch (RuntimeException e) {
            logger.error("Error adding to cart: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> removeFromCart(@RequestParam Long cartItemId){
        try {
            Cart updatedCart = cartService.removeFromCart(cartItemId);
            return ResponseEntity.ok(updatedCart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/removeAll")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Cart> removeAllFromCart(){
            try{
                cartService.removeAllFromCart();;
                return ResponseEntity.ok().build();
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

            }
    }

}

