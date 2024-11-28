package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.ApplicationUser;
import com.example.ecommercemanagement.model.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository extends CrudRepository<WishList, Long> {
    Optional<WishList> findByUser(ApplicationUser user);

}
