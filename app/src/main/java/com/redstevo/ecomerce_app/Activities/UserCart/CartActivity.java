package com.redstevo.ecomerce_app.Activities.UserCart;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.CartItemAdapter;
import com.redstevo.ecomerce_app.Models.CartItemModel;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        super.handleSearching(findViewById(R.id.search_product));
        super.handleUserCartClick(findViewById(R.id.user_cart));
        super.handleTrackOrderClick(findViewById(R.id.track_order));
        super.handleUserProfileClick(findViewById(R.id.user_profile));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<String> images = new ArrayList<>(List.of(
                "http://localhost/images/child1.jpeg",
                "http://localhost/images/child2.jpeg",
                "http://localhost/images/child3.jpeg",
                "http://localhost/images/child4.jpeg",
                "http://localhost/images/child5.jpeg",
                "http://localhost/images/child6.jpeg"
        ));

        List<CartItemModel> cartItemModelList = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            CartItemModel cartItemModel = new CartItemModel(
                    images.get(i),
                    "Children Blue Dresses", 3 + i,
                    2300.00F * i - 1000);

            cartItemModelList.add(cartItemModel);
        }

        populateCartView(recyclerView, cartItemModelList);
    }

    private void populateCartView(RecyclerView recyclerView, List<CartItemModel> cartItemModelList) {
        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartItemModelList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                        false));
        recyclerView.setAdapter(cartItemAdapter);

    }
}