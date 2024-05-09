package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);
}
