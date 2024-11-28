package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.WishListItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListItemRepository extends CrudRepository<WishListItem, Long>{
    List<WishListItem> findByProductId(Long productId);
}
