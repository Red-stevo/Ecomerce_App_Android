package com.redstevo.ecomerce_app.Models;

import android.graphics.Bitmap;

import java.util.List;

public class NewProductModel {
    public NewProductModel(
            String productName, String productDescription, Float productPrice, Float productDiscount,
            Integer productCount, List<Bitmap> productImages, List<ImagePreviewModel> productImagesUri) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productCount = productCount;
        this.productImages = productImages;
        this.productImagesUri = productImagesUri;
        isFilled = true;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Float getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Float productDiscount) {
        this.productDiscount = productDiscount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public List<Bitmap> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<Bitmap> productImages) {
        this.productImages = productImages;
    }

    public List<ImagePreviewModel> getProductImagesUri() {
        return productImagesUri;
    }

    public void setProductImagesUri(List<ImagePreviewModel> productImagesUri) {
        this.productImagesUri = productImagesUri;
    }

    private String productName;

    private String productDescription;

    public Boolean getFilled() {
        return isFilled;
    }

    public void setFilled(Boolean filled) {
        isFilled = filled;
    }

    private Float productPrice;

    private Float productDiscount;

    private Integer productCount;

    private List<Bitmap> productImages;

    private List<ImagePreviewModel> productImagesUri;

    private Boolean isFilled;
}