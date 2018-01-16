package com.askjeffreyliu.walmartlist.model;

import java.util.List;

/**
 * Created by jeff on 1/14/18.
 */

public class ResponseObject {
    private int totalProducts;
    private int pageNumber;
    private int pageSize;
    private int status;
    private String kind;
    private String eTag;
    private List<Product> products;


    public ResponseObject(int totalProducts, int pageNumber, int pageSize, int status, String kind, String eTag, List<Product> products) {
        this.totalProducts = totalProducts;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.status = status;
        this.kind = kind;
        this.eTag = eTag;
        this.products = products;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
