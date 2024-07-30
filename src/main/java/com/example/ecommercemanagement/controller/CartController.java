package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.Cart;
import com.example.ecommercemanagement.service.AuthenticationService;
import com.example.ecommercemanagement.service.CartService;
import com.example.ecommercemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

        @Autowired
        private UserService userService;

        public CartController(CartService cartService, UserService userService)
        {
            this.cartService = cartService;
            this.userService = userService;
        }

        @GetMapping("/list/{userId}")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<Iterable<Cart>> list(@PathVariable int userId){
            ApplicationUser user = userService.FindUserById(userId);
            if( user !=null ){
                Iterable<Cart> carts = cartService.list(userId);
                return ResponseEntity.ok(carts);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

            }

        }

        @PostMapping("/add/{userId}")
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<Cart> addToCart(@PathVariable int userId, @RequestParam Long productId, @RequestParam int quantity) {
            ApplicationUser user = userService.FindUserById(userId);
                if (user != null) {
                    Cart cartItem = cartService.addToCart(productId, quantity, userId);

                    if (cartItem != null) {
                        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }else{
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

