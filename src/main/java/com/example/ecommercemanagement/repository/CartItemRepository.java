package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findByProductId(Long productId);
}

