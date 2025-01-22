package com.redstevo.ecomerce_app.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailsModel {

    private String productName;

    private String productDescription;

    private Integer productCount;

    private Float productPrice;

    private  Float productDiscount;

    private List<String> productUrls;

}
