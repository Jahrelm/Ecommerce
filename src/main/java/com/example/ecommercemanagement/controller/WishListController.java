package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.WishList;
import com.example.ecommercemanagement.service.AuthenticationService;
import com.example.ecommercemanagement.service.UserService;
import com.example.ecommercemanagement.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("http://localhost:3000")
public class WishListController {


    private AuthenticationService authenticationService;
    private WishListService wishListService;
    @Autowired
    private UserService userService;

    public  WishListController(WishListService wishListService, UserService userService){
        this.wishListService = wishListService;
        this.userService = userService;
    }

    @GetMapping("/list/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Iterable<WishList>> list(@PathVariable int userId){
        try{
            Iterable<WishList> wishLists = wishListService.list(userId);
            return ResponseEntity.ok(wishLists);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping("/add/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WishList> addToWishList(@RequestParam Long productId,@RequestParam int quantity, @PathVariable int userId){
        System.out.println("productId: " + productId);
        System.out.println("userId: " + userId);
        System.out.println("quantity: " + quantity);
        ApplicationUser user = userService.FindUserById(userId);
        if(user != null){
            WishList wishList = wishListService.addToWishList(productId, quantity, userId);
            if (wishList != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(wishList);
            }else {
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/moveToCart/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<WishList> addToWishListToCart(@PathVariable int userId){
        try{
            WishList wishList = wishListService.addToWishListToCart(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(wishList);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('User)")
    public ResponseEntity<WishList> removeFromWishList(@RequestParam Long wishListId){
        wishListService.removeFromWishList(wishListId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/removeAll")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<WishList> removeAllFromWishList(){
        try{
            wishListService.removeAllFromWishList();
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }
}
