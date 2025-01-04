package com.redstevo.ecomerce_app.Services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redstevo.ecomerce_app.Models.NewProductModel;

import java.util.List;


public class NewProductService {
    FirebaseDatabase database;

    public NewProductService() {
        this.database = FirebaseDatabase.getInstance();
    }

    public String saveNewProduct(List<NewProductModel> newProductModelList) {
        DatabaseReference newProductRef = database.getReference("products");


        return null;
    }

}
