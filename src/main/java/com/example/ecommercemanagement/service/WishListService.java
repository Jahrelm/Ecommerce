package com.example.ecommercemanagement.service;


import com.example.ecommercemanagement.model.*;
import com.example.ecommercemanagement.repository.ProductRepository;
import com.example.ecommercemanagement.repository.UserRepository;
import com.example.ecommercemanagement.repository.WishListItemRepository;
import com.example.ecommercemanagement.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class WishListService {
    //private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final WishListRepository wishListRepository;

    private final WishListItemRepository wishListItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;


    public WishListService(WishListRepository wishListRepository, WishListItemRepository wishListItemRepository,
                           ProductRepository productRepository, UserRepository userRepository){

        this.wishListRepository = wishListRepository;
        this.wishListItemRepository = wishListItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<WishList> findWishListsByProductId(Long productId) {
        List<WishListItem> wishListItems = wishListItemRepository.findByProductId(productId);
        return wishListItems.stream()
                .map(WishListItem::getWishList)
                .distinct()
                .collect(Collectors.toList());
    }

    public Iterable<WishList> list(int userId){
        ApplicationUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID" + userId + "not found"));
        Optional<WishList> wishListOptional = wishListRepository.findByUser(user);
        return wishListOptional.map(Collections::singletonList).orElse(Collections.emptyList());
    }
    public WishList addToWishList(long productId, int quantity, int userId){
        ApplicationUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not Found with ID: " + userId));

        Optional<Product> productOptional  = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new RuntimeException("Product with ID " + productId + "not found");
        }
        Product product = productOptional.get();
        WishList wishList = wishListRepository.findByUser(user).orElseGet(() -> {
            WishList newWishList = new WishList();
            newWishList.setUser(user);
            return wishListRepository.save(newWishList);

        });

        Optional<WishListItem> wishListItemOptional = wishList.getWishListItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        WishListItem wishListItem;
        if(wishListItemOptional.isPresent()){
            wishListItem = wishListItemOptional.get();
            wishListItem.setQuantity(wishListItem.getQuantity() + quantity);
        } else {
            wishListItem = new WishListItem();
            wishListItem.setWishList(wishList);
            wishListItem.setProduct(product);
            wishListItem.setQuantity(quantity);
            wishList.getWishListItems().add(wishListItem);
        }
        wishListItem.calculateSubTotal();
        wishList.setTotalCost(wishList.getWishListItems().stream().mapToDouble(WishListItem::getSubTotal).sum());

        return wishListRepository.save(wishList);

    }



    





}
