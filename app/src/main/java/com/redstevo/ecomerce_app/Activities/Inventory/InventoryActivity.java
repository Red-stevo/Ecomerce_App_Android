package com.redstevo.ecomerce_app.Activities.Inventory;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.InventoryAdapter;
import com.redstevo.ecomerce_app.Models.InventoryItem;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends GeneralActivity {

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_view);
       // FirebaseApp.initializeApp(this);



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");

        RecyclerView recyclerView = findViewById(R.id.rvInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<InventoryItem> inventoryItem = new ArrayList<>();
        InventoryAdapter inventoryAdapter = new InventoryAdapter(inventoryItem);
        recyclerView.setAdapter(inventoryAdapter);

        Log.d("CHECK", "Checking reference path: " + databaseReference.toString());
        Log.d("CHECK","checking if data is present in "+ databaseReference.getKey());




    }
}
