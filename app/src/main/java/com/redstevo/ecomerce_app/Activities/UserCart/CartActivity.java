package com.redstevo.ecomerce_app.Activities.UserCart;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.CartItemAdapter;
import com.redstevo.ecomerce_app.Models.CartItemModel;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends GeneralActivity {
    private final DatabaseReference reference;
    public CartActivity() {
        reference = FirebaseDatabase
                .getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/")
                .getReference();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        super.handleSearching(findViewById(R.id.search_product));
        super.handleUserCartClick(findViewById(R.id.user_cart));
        super.handleTrackOrderClick(findViewById(R.id.track_order));
        super.handleUserProfileClick(findViewById(R.id.user_profile));

        populateCartView(findViewById(R.id.recyclerView), getUserCartItems("AAA", this));
    }

    private void populateCartView(RecyclerView recyclerView, List<CartItemModel> cartItemModelList) {
        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemModelList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                        false));
        recyclerView.setAdapter(cartItemAdapter);

    }


    private List<CartItemModel> getUserCartItems(String userId, Context context) {
        List<CartItemModel> cartItems = new ArrayList<>();
       /* String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();*/

        reference.child("cart" + userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.d("FETCH CART", "onDataChange: "+String.valueOf(dataSnapshot.getValue()));


                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            CartItemModel cartItem = snapshot.getValue(CartItemModel.class);
                            Log.d("FETCH CART", "onDataChange: "+String.valueOf(snapshot));
                            if (cartItem != null) {
                                cartItems.add(cartItem);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("FETCH CART", "Error: ");
                        Toast.makeText(context,
                                "Failed to load cart items: " + databaseError.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        return cartItems;
    }
}
