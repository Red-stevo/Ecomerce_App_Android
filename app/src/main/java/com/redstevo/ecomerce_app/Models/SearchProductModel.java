package com.redstevo.ecomerce_app.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SearchProductModel {

    private String productId;

    private String productName;

    private String productUrl;

    private Float productPrice;

    private Float productDiscount;
}
