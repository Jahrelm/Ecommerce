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
    @Column(name= "product_id")
    private Long productId;


    private String image;
    private String brand;
    private int review;
    private String title;
    private String offerPrice;
    private String price;
    private boolean campaignProduct;
    private Integer campaignProductAvailable;
    private Integer campaignProductSale;
    private int stock;
    private String currency;
    private String productType;



    public Product() {
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return currency;
    }
    public Integer getStock(){
        return stock;
    }

    public Long getId() {
        return productId;
    }

    public void setId(Long id) {
        this.productId = productId;
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
