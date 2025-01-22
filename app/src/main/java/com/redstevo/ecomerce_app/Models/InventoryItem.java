package com.redstevo.ecomerce_app.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class InventoryItem {
    private String name;
    private int count;
    private double price;
}
