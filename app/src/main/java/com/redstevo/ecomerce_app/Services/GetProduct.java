package com.redstevo.ecomerce_app.Services;

import android.util.Log;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.redstevo.ecomerce_app.Models.ProductDetailsModel;

public class GetProduct {

    private final DatabaseReference reference;

    public GetProduct() {
        this.reference = FirebaseDatabase.getInstance().getReference("products");
    }


    public ProductDetailsModel getProductByKey(String key){

        final ProductDetailsModel[] productDetailsModel = new ProductDetailsModel[1];

        reference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    productDetailsModel[0] = snapshot.getValue(ProductDetailsModel.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return productDetailsModel[0];
    }
}
