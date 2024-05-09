package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
