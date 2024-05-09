package com.example.ecommercemanagement.controller;

import com.example.ecommercemanagement.model.Product;
import com.example.ecommercemanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/list")
    public Iterable<Product> list() {

        return productService.list();
    }

    @GetMapping("/list/{id}")
    public Product findProductById(@PathVariable Long id){
        return productService.findProductById(id);

    }



}
