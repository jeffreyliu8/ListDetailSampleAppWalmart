package com.askjeffreyliu.walmartlist.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jeff on 1/14/18.
 */

public class Product implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productId);
        dest.writeString(this.productName);
        dest.writeString(this.shortDescription);
        dest.writeString(this.longDescription);
        dest.writeString(this.price);
        dest.writeString(this.productImage);
        dest.writeFloat(this.reviewRating);
        dest.writeInt(this.reviewCount);
        dest.writeByte(this.inStock ? (byte) 1 : (byte) 0);
    }

    protected Product(Parcel in) {
        this.productId = in.readString();
        this.productName = in.readString();
        this.shortDescription = in.readString();
        this.longDescription = in.readString();
        this.price = in.readString();
        this.productImage = in.readString();
        this.reviewRating = in.readFloat();
        this.reviewCount = in.readInt();
        this.inStock = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
