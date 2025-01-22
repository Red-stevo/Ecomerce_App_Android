package com.redstevo.ecomerce_app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItemModel {

    private String cartId;

    private String imageUrl;

    private String productName;

    private Integer productQuantity;

    private Float productPrice;

}

