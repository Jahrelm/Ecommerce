package com.example.ecommercemanagement.repository;

import com.example.ecommercemanagement.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
