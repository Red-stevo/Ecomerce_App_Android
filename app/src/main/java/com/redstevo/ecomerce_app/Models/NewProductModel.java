package com.redstevo.ecomerce_app.Models;

import android.graphics.Bitmap;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
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