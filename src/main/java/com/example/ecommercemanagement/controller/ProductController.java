package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/list")
    public Iterable<Product> list() {
        return productService.list();
    }
}
