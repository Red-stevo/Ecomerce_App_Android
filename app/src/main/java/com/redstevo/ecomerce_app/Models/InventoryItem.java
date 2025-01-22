package com.redstevo.ecomerce_app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class InventoryItem {
    private String name;
    private int count;
    private double price;
    private String imageUrl;
}
