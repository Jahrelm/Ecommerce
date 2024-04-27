package com.example.ecommercemanagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String image;
    private String brand;
    private int review;
    private String title;
    private String offerPrice;
    private String price;
    private boolean campaignProduct;
    private Integer campaignProductAvailable;
    private Integer campaignProductSale;
    private String productType;

    public Product () {

    }




}
