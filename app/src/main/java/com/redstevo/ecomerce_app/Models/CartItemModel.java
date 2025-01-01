package com.redstevo.ecomerce_app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CartItemModel {
    private String imageUrl;

    private String productName;

    private Integer productQuantity;

    private Float productPrice;
}
