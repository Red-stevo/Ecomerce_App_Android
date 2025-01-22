package com.redstevo.ecomerce_app.Services;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redstevo.ecomerce_app.Accessories.AccessoriesImpl;
import com.redstevo.ecomerce_app.Accessories.InputCheck;
import com.redstevo.ecomerce_app.Accessories.OnImageUploadComplete;
import com.redstevo.ecomerce_app.Models.NewProductModel;
import com.redstevo.ecomerce_app.Models.ProductModel;

import java.util.List;
import java.util.UUID;

public class NewProductService {

    private final DatabaseReference reference;
    private final InputCheck accessory;

    private final MeiliSearchService meiliSearchService;

    public NewProductService() {
        reference = FirebaseDatabase
                .getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/")
                .getReference("products");;
        accessory = new AccessoriesImpl();
        meiliSearchService = new MeiliSearchService();

    }

    public void saveNewProduct(List<NewProductModel> newProductModelList, Context context) {

        newProductModelList.forEach(product -> accessory
                        .uploadImages(context, product.getProductImages(), new OnImageUploadComplete() {
            @Override
            public void onComplete(List<String> imageUrls) {
                ProductModel productModel = new ProductModel(UUID.randomUUID().toString(),
                        product.getProductName(), product.getProductDescription(), imageUrls,
                        product.getProductPrice(), product.getProductDiscount(), product.getProductCount());

                reference.child(productModel.getProductId())
                        .setValue(productModel)
                        .addOnFailureListener(e -> {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }).addOnSuccessListener(unused -> {
                            meiliSearchService.addProducts(productModel, context);
                        });
            }
            @Override
            public void onError(Exception exception) {
                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));

    }
}
