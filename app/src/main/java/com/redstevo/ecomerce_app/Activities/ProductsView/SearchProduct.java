package com.redstevo.ecomerce_app.Activities.ProductsView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;

public class SearchProduct extends GeneralActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = super.getSharedPreferences();

        String query = sharedPreferences.getString("query", "Related Products");

        Log.d("QUERY", "onCreate: "+query);
    }
}
