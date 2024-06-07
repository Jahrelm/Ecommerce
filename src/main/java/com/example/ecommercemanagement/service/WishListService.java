package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.model.WishList;
import com.example.ecommercemanagement.repository.ProductRepository;
import com.example.ecommercemanagement.repository.WishListRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Service
public class WishListService {

    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;
    private final CartService cartService;

    public WishListService(ProductRepository productRepository, WishListRepository wishListRepository, CartService cartService){
        this.productRepository = productRepository;
        this.wishListRepository = wishListRepository;
        this.cartService = cartService;
    }

    public Iterable<WishList> list(){
        return wishListRepository.findAll();
    }

    public WishList addToWishList(@RequestParam Long productId, @RequestParam int quantity){
        Optional<Product> productOptional = productRepository.findById(productId);

        if (!productOptional.isPresent()){
            return null;
        }

        Product product = productOptional.get();
        Optional<WishList> wishListOptional = wishListRepository.findByProductId(productId);

        WishList wishList;
        if(wishListOptional.isPresent()){
            wishList = wishListOptional.get();
            wishList.setQuantity(wishList.getQuantity() + quantity);
        } else {
            wishList = new WishList();
            wishList.setProductId(productId);
            wishList.setQuantity(quantity);
            wishList.setTitle(product.getTitle());
            wishList.setBrand(product.getBrand());
            wishList.setPrice(product.getPrice());
        }
        double totalCost = calculateTotalCost(product.getPrice(), wishList.getQuantity());
        wishList.setTotalCost(totalCost);

        return wishListRepository.save(wishList);

    }

    private double calculateTotalCost(String price, int quantity){
        double productPrice = Double.parseDouble(price.replace("$", ""));
        return productPrice * quantity;
    }

    public void removeFromWishList(@RequestParam Long wishListId){
        wishListRepository.deleteById(wishListId);

    }
    public void removeAllFromWishList(){
        wishListRepository.deleteAll();
    }

    /*
    public void moveToCart(Long wishListId, int quantity){
        Optional<WishList> wishListOptional = wishListRepository.findById(wishListId);
        if (!wishListOptional.isPresent()) {
            return;
        }

        WishList wishList = wishListOptional.get();
        Long productId = wishList.getProductId();

        // Add to cart
        cartService.addToCart(productId, quantity);

        // Remove from wishlist
        if (wishList.getQuantity() <= quantity) {
            wishListRepository.deleteById(wishListId);
        } else {
            wishList.setQuantity(wishList.getQuantity() - quantity);
            wishList.setTotalCost(calculateTotalCost(wishList.getPrice(), wishList.getQuantity()));
            wishListRepository.save(wishList);
        }
        */


    public void moveAllToCart(){
        Iterable<WishList> wishListItems = wishListRepository.findAll();
        for (WishList wishListItem : wishListItems){
            cartService.addToCart(wishListItem.getProductId(), wishListItem.getQuantity());
        }
        wishListRepository.deleteAll();
    }
}
