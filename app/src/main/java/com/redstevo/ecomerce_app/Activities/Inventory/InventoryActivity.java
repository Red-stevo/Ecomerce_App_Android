package com.redstevo.ecomerce_app.Activities.Inventory;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.InventoryAdapter;
import com.redstevo.ecomerce_app.Models.InventoryItem;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.List;


public class InventoryActivity extends GeneralActivity {

    DatabaseReference databaseReference;
    private InventoryAdapter inventoryAdapter;
    private List<InventoryItem> inventoryItem;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_view);
       // FirebaseApp.initializeApp(this);



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("cartAAA");

        RecyclerView recyclerView = findViewById(R.id.rvInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inventoryItem = new ArrayList<>();
        inventoryAdapter = new InventoryAdapter(inventoryItem);
        recyclerView.setAdapter(inventoryAdapter);

        Log.d("CHECK", "Checking reference path: " + databaseReference.getPath());
        Log.d("CHECK","checking if data is present in "+ databaseReference.getKey());




        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("DATA", "the data is "+snapshot.getChildren());
                Log.d("INVENTORY"," "+snapshot);
                for (DataSnapshot snapshot1 : snapshot.getChildren()){



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
