package com.redstevo.ecomerce_app.Activities.ProductsView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Adapters.SearchProductAdapter;
import com.redstevo.ecomerce_app.Models.SearchProductModel;
import com.redstevo.ecomerce_app.R;
import com.redstevo.ecomerce_app.Services.MeiliSearchService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchProduct extends GeneralActivity {

    private final MeiliSearchService meiliSearchService;

    public SearchProduct() {
        meiliSearchService = new MeiliSearchService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        super.handleSearching(findViewById(R.id.search_product));
        super.handleUserCartClick(findViewById(R.id.user_cart));
        super.handleTrackOrderClick(findViewById(R.id.track_order));
        super.handleUserProfileClick(findViewById(R.id.user_profile));
        SharedPreferences sharedPreferences = super.getSharedPreferences();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        getSearchProducts(sharedPreferences.getString("query", " "), recyclerView);


        //add event listener to the search bar.
        EditText searchBar = findViewById(R.id.search_product);

        searchBar.setOnKeyListener((v, keyCode, event) -> {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
               getSearchProducts(String.valueOf(searchBar.getText()), recyclerView);
                Log.d("SEARCH_BAR", "onKey: "+searchBar.getText().toString());
                searchBar.setText("");
            }
            return true;
        });
    }

    private void getSearchProducts(String query, RecyclerView recyclerView) {
        meiliSearchService.searchProducts(query, this, recyclerView);
    }

}
