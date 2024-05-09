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


    public Product(Long id, String image, String brand, int review, String title, String offerPrice, String price, boolean campaignProduct, Integer campaignProductAvailable, Integer campaignProductSale, String productType) {
        this.id = id;
        this.image = image;
        this.brand = brand;
        this.review = review;
        this.title = title;
        this.offerPrice = offerPrice;
        this.price = price;
        this.campaignProduct = campaignProduct;
        this.campaignProductAvailable = campaignProductAvailable;
        this.campaignProductSale = campaignProductSale;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isCampaignProduct() {
        return campaignProduct;
    }

    public void setCampaignProduct(boolean campaignProduct) {
        this.campaignProduct = campaignProduct;
    }

    public Integer getCampaignProductAvailable() {
        return campaignProductAvailable;
    }

    public void setCampaignProductAvailable(Integer campaignProductAvailable) {
        this.campaignProductAvailable = campaignProductAvailable;
    }

    public Integer getCampaignProductSale() {
        return campaignProductSale;
    }

    public void setCampaignProductSale(Integer campaignProductSale) {
        this.campaignProductSale = campaignProductSale;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
