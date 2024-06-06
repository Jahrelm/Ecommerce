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

    public WishListService(ProductRepository productRepository, WishListRepository wishListRepository){
        this.productRepository = productRepository;
        this.wishListRepository = wishListRepository;
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

        return wishListRepository.save(wishList);

    }

    public void removeFromWishList(@RequestParam Long wishListId){
        wishListRepository.deleteById(wishListId);

    }
    public void removeAllFromWishList(){
        wishListRepository.deleteAll();
    }
}
