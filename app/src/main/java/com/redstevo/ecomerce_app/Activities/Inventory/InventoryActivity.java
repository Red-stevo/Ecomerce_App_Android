package com.redstevo.ecomerce_app.Activities.Inventory;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import lombok.extern.slf4j.Slf4j;


public class InventoryActivity extends GeneralActivity {

    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private InventoryAdapter inventoryAdapter;
    private List<InventoryItem> inventoryItem;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_view);



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com");
        databaseReference = firebaseDatabase.getReference("products");

        recyclerView = findViewById(R.id.rvInventory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inventoryItem = new ArrayList<>();
        inventoryAdapter = new InventoryAdapter(inventoryItem);
        recyclerView.setAdapter(inventoryAdapter);

        Log.d("CHECK","checking if data is present in "+ databaseReference.getKey());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("DATA"," list is"+dataSnapshot);
                InventoryItem item = dataSnapshot.getValue(InventoryItem.class);
                System.out.println(item);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("DATA", "the data is "+snapshot.getChildren());
                inventoryItem.clear();
                Log.d("INVENTORY"," "+snapshot);
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    InventoryItem item = new InventoryItem();
                    item.setName(snapshot1.child("productName").getValue(String.class));
                    item.setCount(snapshot1.child("count").getValue(Integer.class));
                    item.setPrice(snapshot1.child("price").getValue(Double.class));

                    inventoryItem.add(item);
                }
                inventoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}
