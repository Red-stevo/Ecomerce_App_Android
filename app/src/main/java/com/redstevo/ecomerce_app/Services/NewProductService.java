package com.redstevo.ecomerce_app.Services;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.redstevo.ecomerce_app.Accessories.AccessoriesImpl;
import com.redstevo.ecomerce_app.Accessories.InputCheck;
import com.redstevo.ecomerce_app.Accessories.OnImageUploadComplete;
import com.redstevo.ecomerce_app.Models.NewProductModel;
import com.redstevo.ecomerce_app.Models.ProductModel;

import java.util.List;


public class NewProductService {
    private final FirebaseFirestore database;

    private final InputCheck accessory;

    public NewProductService() {
        this.database = FirebaseFirestore.getInstance();
        accessory = new AccessoriesImpl();
    }

    public void saveNewProduct(List<NewProductModel> newProductModelList, Context context) {

        newProductModelList.forEach(product -> {
            accessory.uploadImages(context, product.getProductImages(), new OnImageUploadComplete() {
                @Override
                public void onComplete(List<String> imageUrls) {
                    ProductModel productModel = new ProductModel(product.getProductName(),
                            product.getProductDescription(), imageUrls, product.getProductPrice(),
                            product.getProductDiscount());

                    database.collection("products")
                            .add(productModel)
                            .addOnFailureListener(e ->  {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }
                @Override
                public void onError(Exception exception) {
                    Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
