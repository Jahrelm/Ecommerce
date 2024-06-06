package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.WishList;
import com.example.ecommercemanagement.service.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("http://localhost:3000")
public class WishListController {

    private WishListService wishListService;

    public WishListController (WishListService wishListService){
        this.wishListService = wishListService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public Iterable<WishList>list(){
        return wishListService.list();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WishList> addToWishList(@RequestParam Long productId, @RequestParam int quantity){
        WishList wishList = wishListService.addToWishList(productId, quantity);
        if (wishList != null){
           return ResponseEntity.status(HttpStatus.CREATED).body(wishList);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WishList> removeFromWishList(@RequestParam Long wishListItemId){
        wishListService.removeFromWishList(wishListItemId);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/removeAll")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WishList> removeAllFromWishList(){
        try{
            wishListService.removeAllFromWishList();
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}
