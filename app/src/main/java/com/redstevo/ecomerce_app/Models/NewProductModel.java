package com.redstevo.ecomerce_app.Models;

import android.graphics.Bitmap;

import org.w3c.dom.Text;

import java.util.List;

public class NewProductModel {


    public NewProductModel(
            String productName, String productDescription, Integer productCount,
            Double productPrice, Double productDiscount, List<Bitmap> productImages) {

        this.productName = productName;
        this.productDescription = productDescription;
        this.productCount = productCount;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productImages = productImages;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Double getProductDiscount() {
        return productDiscount;
    }

    public List<Bitmap> getProductImages() {
        return productImages;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductDiscount(Double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public void setProductImages(List<Bitmap> productImages) {
        this.productImages = productImages;
    }

    private String productName;

    private String productDescription;

    private Integer productCount;

    private Double productPrice;

    private Double productDiscount;

    private List<Bitmap> productImages;
}