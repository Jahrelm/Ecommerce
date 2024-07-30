package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByProductId(Long productId);
    Optional<Cart> findByUserId(int userId);


}
