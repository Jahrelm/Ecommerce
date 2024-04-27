package com.example.ecommercemanagement.service;

import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Iterable<Product> list(){
        return productRepository.findAll();
    }
    public Product save(Product product){
        return productRepository.save(product);
    }

    public Iterable<Product> save(List<Product> products){
        return productRepository.saveAll(products);

    }

}
