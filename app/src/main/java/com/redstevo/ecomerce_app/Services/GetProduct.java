package com.redstevo.ecomerce_app.Services;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.redstevo.ecomerce_app.Adapters.ProductImageAdapter;
import com.redstevo.ecomerce_app.Models.CartItemModel;
import com.redstevo.ecomerce_app.Models.ProductModel;

import java.text.DecimalFormat;
import java.util.UUID;

public class GetProduct {

    public void getProductByKey(
            String key, Context context, ImageView productImageView, TextView productTitleView,
            RecyclerView selectImageView, TextView productDescriptionView, TextView productCountView,
            TextView productPriceView, TextView productDiscountPercentageView, Button addCart) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("products");

        reference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ProductModel detailsModel = snapshot.getValue(ProductModel.class);

                    if (detailsModel == null) {
                        Log.e("getProductByKey", "ProductModel is null");
                        return;
                    }

                    Log.d("getProductByKey", "Product details: " + snapshot);

                    // Set product images in RecyclerView
                    setupProductImages(context, productImageView, selectImageView, detailsModel);

                    // Populate product details
                    populateProductDetails(detailsModel, productTitleView, productDescriptionView,
                            productPriceView, productCountView, productDiscountPercentageView);

                    // Handle Add to Cart button click
                    setupAddToCartButton(context, addCart, detailsModel);
                } else {
                    Log.e("getProductByKey", "No data found for key: " + key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("getProductByKey", "Database operation cancelled: "+error.getMessage());
            }
        });
    }

    private void setupProductImages(Context context, ImageView productImageView,
                                    RecyclerView selectImageView, ProductModel detailsModel) {
        ProductImageAdapter productImageAdapter = new ProductImageAdapter(
                context, productImageView, detailsModel.getProductUrls());

        selectImageView.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));
        selectImageView.setHasFixedSize(false);
        selectImageView.setAdapter(productImageAdapter);
    }

    private void populateProductDetails(
            ProductModel detailsModel, TextView productTitleView, TextView productDescriptionView,
            TextView productPriceView, TextView productCountView, TextView productDiscountPercentageView) {
        productTitleView.setText(detailsModel.getProductName());
        productDescriptionView.setText(detailsModel.getProductDescription());
        productPriceView.setText(String.format("KSH %s", detailsModel.getProductPrice()));
        productCountView.setText(String.valueOf(detailsModel.getProductCount()));

        double discountPercentage =
                (detailsModel.getProductDiscount() / detailsModel.getProductPrice()) * 100;
        String formattedDiscount = new DecimalFormat("#.00").format(discountPercentage)
                + " % OFF";
        productDiscountPercentageView.setText(formattedDiscount);
    }

    private void setupAddToCartButton(Context context, Button addCart, ProductModel detailsModel) {
        addCart.setOnClickListener(view -> {
            CartItemModel cartData = new CartItemModel(
                    UUID.randomUUID().toString(),
                    detailsModel.getProductUrls().get(0),
                    detailsModel.getProductName(),
                    1,
                    detailsModel.getProductPrice()
            );

            String userId = "AAA";
            DatabaseReference reference = FirebaseDatabase
                    .getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/")
                    .getReference("cart"+userId);

            reference.child(cartData.getCartId())
                    .setValue(cartData)
                    .addOnSuccessListener(unused ->
                            Toast.makeText(context, "Product Added To Cart",
                                    Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(context, "Failed to Add Product: "
                                    + e.getMessage(), Toast.LENGTH_LONG).show());
        });
    }

}
