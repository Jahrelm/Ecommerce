package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.dto.StripeResponse;
import com.example.ecommercemanagement.model.CartItem;
import com.example.ecommercemanagement.service.StripeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products-checkout")
public class ProductCheckoutController {

    private StripeService stripeService;

    public ProductCheckoutController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody CartItem cartItem){
        StripeResponse stripeResponse = stripeService.checkoutProducts(cartItem);
        return ResponseEntity
                .status(HttpStatus.OK)
        .body(stripeResponse);

    }
}
