package com.askjeffreyliu.walmartlist.model;


/**
 * Created by jeff on 1/14/18.
 */

public class Product {
    private String productId;
    private String productName;
    private String shortDescription;
    private String longDescription;
    private String price;
    private String productImage;
    private float reviewRating;
    private int reviewCount;
    private boolean inStock;

    public Product(String productId, String productName, String shortDescription, String longDescription, String price, String productImage, float reviewRating, int reviewCount, boolean inStock) {
        this.productId = productId;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.productImage = productImage;
        this.reviewRating = reviewRating;
        this.reviewCount = reviewCount;
        this.inStock = inStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getPrice() {
        return price;
    }

    public String getProductImage() {
        return productImage;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }
}
