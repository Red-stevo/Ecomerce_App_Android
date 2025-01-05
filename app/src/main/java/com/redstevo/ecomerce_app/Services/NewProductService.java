package com.redstevo.ecomerce_app.Services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redstevo.ecomerce_app.Accessories.AccessoriesImpl;
import com.redstevo.ecomerce_app.Accessories.InputCheck;
import com.redstevo.ecomerce_app.Accessories.OnImageUploadComplete;
import com.redstevo.ecomerce_app.Models.NewProductModel;

import java.util.List;


public class NewProductService {
    FirebaseDatabase database;

    InputCheck accessory;

    public NewProductService() {
        this.database = FirebaseDatabase.getInstance();
        accessory = new AccessoriesImpl();
    }

    public String saveNewProduct(List<NewProductModel> newProductModelList) {
        DatabaseReference newProductRef = database.getReference("products");

        newProductModelList.forEach(product -> {
            accessory.uploadImages(product.getProductImages(), new OnImageUploadComplete() {
                @Override
                public void onComplete(List<String> imageUrls) {
                    product.getProductImagesUri().forEach(model -> {

                    });
                }

                @Override
                public void onError(Exception exception) {

                }
            });
        });

        return null;
    }

}
