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
    public Product findProductById(Long id){
        return productRepository.findById(id)
                .orElse(null);
    }

    public List<Product> findByTitleContainingIgnoreCase(String title){
        return productRepository.findByTitleContainingIgnoreCase(title);

    }

    public long count() {
        return productRepository.count();
    }

    public void reduceStock(Long productId, int quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        if (product.getStock() < quantity){
            throw new RuntimeException("Insufficient stock for product:" + product.getTitle());
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

}
