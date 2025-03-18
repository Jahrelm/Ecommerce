package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.dto.StripeResponse;
import com.example.ecommercemanagement.model.CartItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    private final ProductService productService;
    @Value("${stripe.secretKey}")
    private String secretKey;


    public StripeService(ProductService productService) {
        this.productService = productService;
    }


    public StripeResponse checkoutProducts(CartItem cartItem) {
        Stripe.apiKey = secretKey;
        try {
            SessionCreateParams.LineItem.PriceData.ProductData productData =
                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                            .setName(cartItem.getProduct().getTitle())
                            .build();

            SessionCreateParams.LineItem.PriceData priceData =
                    SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency(cartItem.getProduct().getCurrency() != null ? cartItem.getProduct().getCurrency() : "usd")
                            .setUnitAmount((long) (Double.parseDouble(cartItem.getProduct().getPrice().replace("$", "")) * 100)) // Convert to smallest currency unit
                            .setProductData(productData)
                            .build();

            SessionCreateParams.LineItem lineItem =
                    SessionCreateParams.LineItem.builder()
                            .setPriceData(priceData)
                            .setQuantity((long) cartItem.getQuantity())
                            .build();

            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl("http://localhost:3000/payment-success")
                            .setCancelUrl("http://localhost:8080/cancel")
                            .addLineItem(lineItem)
                            .build();

            Session session = Session.create(params);

            // Reduce stock after creating the session
            productService.reduceStock(cartItem.getProduct().getProductId(), cartItem.getQuantity());

            return new StripeResponse(
                    session.getId(),
                    session.getUrl(),
                    "SUCCESS",
                    "Payment session created."
            );

        } catch (StripeException ex) {
            throw new RuntimeException("Stripe API error: " + ex.getMessage(), ex);
        }
    }
}


