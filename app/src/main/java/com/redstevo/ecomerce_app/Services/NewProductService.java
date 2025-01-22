package com.redstevo.ecomerce_app.Services;

import android.content.Context;
import android.widget.Toast;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redstevo.ecomerce_app.Accessories.AccessoriesImpl;
import com.redstevo.ecomerce_app.Accessories.InputCheck;
import com.redstevo.ecomerce_app.Accessories.OnImageUploadComplete;
import com.redstevo.ecomerce_app.Models.NewProductModel;
import com.redstevo.ecomerce_app.Models.ProductModel;

import java.util.List;

public class NewProductService {

    private final DatabaseReference reference;
    private final InputCheck accessory;

    public NewProductService() {
        reference = FirebaseDatabase
                .getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/")
                .getReference();;
        accessory = new AccessoriesImpl();

    }

    public void saveNewProduct(List<NewProductModel> newProductModelList, Context context) {

        newProductModelList.forEach(product -> {
            accessory.uploadImages(context, product.getProductImages(), new OnImageUploadComplete() {
                @Override
                public void onComplete(List<String> imageUrls) {
                    ProductModel productModel = new ProductModel(product.getProductName(),
                            product.getProductDescription(), imageUrls, product.getProductPrice(),
                            product.getProductDiscount(), product.getProductCount());

                    String key = reference.child("products").push().getKey();

                     if (key != null){
                         reference.child(key)
                             .setValue(productModel)
                             .addOnFailureListener(e -> {
                                 Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                             }).addOnSuccessListener(unused -> {
                                 /*saving products data to algolia.*/
                                 SearchClient client =
                                         DefaultSearchClient.create("R9W4M96A8S",
                                                 "6dbcc8015a29941136670834d6bc7299");

                                 SearchIndex<ProductModel> index = client
                                         .initIndex("products", ProductModel.class);
                                 index.saveObject(productModel).waitTask();
                             });
                     }else
                         Toast.makeText(context, "Error Saving product", Toast.LENGTH_LONG).show();

                }
                @Override
                public void onError(Exception exception) {
                    Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
