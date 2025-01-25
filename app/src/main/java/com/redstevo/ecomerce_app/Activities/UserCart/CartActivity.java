package com.redstevo.ecomerce_app.Activities.UserCart;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
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
import java.util.Objects;

public class CartActivity extends GeneralActivity {
    private final DatabaseReference reference;

    public CartActivity() {
         String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        reference = FirebaseDatabase
                .getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/")
                .getReference("cart" + userId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        super.handleSearching(findViewById(R.id.search_product));
        super.handleUserCartClick(findViewById(R.id.user_cart));
        super.handleTrackOrderClick(findViewById(R.id.track_order));
        super.handleUserProfileClick(findViewById(R.id.user_profile));

        getUserCartItems(this, findViewById(R.id.recyclerView));
    }

    private void populateCartView(RecyclerView recyclerView, List<CartItemModel> cartItemModelList) {
        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemModelList, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                        false));
        recyclerView.setAdapter(cartItemAdapter);

    }


    private void getUserCartItems(Context context, RecyclerView recyclerView) {
        List<CartItemModel> cartItems = new ArrayList<>();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CartItemModel cartItem = snapshot.getValue(CartItemModel.class);
                    if (cartItem != null) cartItems.add(cartItem);
                }
                populateCartView(recyclerView, cartItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context,
                        "Failed to load cart items: " + databaseError.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
