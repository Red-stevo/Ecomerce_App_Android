package com.redstevo.ecomerce_app.Activities.Inventory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.redstevo.ecomerce_app.Activities.AddProduct.AddProductActivity;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.InventoryAdapter;
import com.redstevo.ecomerce_app.Models.InventoryItem;
import com.redstevo.ecomerce_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends GeneralActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        super.handleUserProfileClick(findViewById(R.id.user_profile));

        /*add event listener.*/
        ImageView imageView = findViewById(R.id.user_cart);
        Picasso
                .get()
                .load(R.drawable.inventory_icon)
                .into(imageView);
        imageView.setOnClickListener(view -> {
            startActivity(new Intent(this, InventoryActivity.class));
        });

        ImageView addProduct = findViewById(R.id.track_order);
        Picasso
                .get()
                .load(R.drawable.new_product_icon)
                .into(addProduct);

        addProduct.setOnClickListener(view -> {
            startActivity(new Intent(this, AddProductActivity.class));
        });

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<InventoryItem> inventoryItem = new ArrayList<>();
        InventoryAdapter inventoryAdapter = new InventoryAdapter(inventoryItem);
        recyclerView.setAdapter(inventoryAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                inventoryItem.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String productName = dataSnapshot.child("productName").getValue(String.class);
                    Integer productCount = dataSnapshot.child("productCount").getValue(Integer.class);
                    Float productPrice = dataSnapshot.child("productPrice").getValue(Float.class);
                    List<String> imageUrls = new ArrayList<>();
                    for (DataSnapshot imageSnapshot : dataSnapshot.child("productUrls").getChildren()) {
                        String imageUrl = imageSnapshot.getValue(String.class);
                        if (imageUrl != null) {
                            imageUrls.add(imageUrl);
                        }
                    }
                    String imageUrl = imageUrls.isEmpty() ? null : imageUrls.get(0);
                    if (productName != null && productCount != null && productPrice != null) {
                        inventoryItem.add(new InventoryItem(productName, productCount, productPrice,imageUrl));

                    }
                }
                inventoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(InventoryActivity.this,"not found",Toast.LENGTH_LONG).show();
            }
        });


    }
}
