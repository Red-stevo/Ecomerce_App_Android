package com.redstevo.ecomerce_app.Activities.ProductsView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.R;

public class SearchProduct extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        SharedPreferences sharedPreferences = super.getSharedPreferences();

        String query = sharedPreferences.getString("query", "Related Products");

        Log.d("QUERY", "onCreate: "+query);

        //add event listener to the search bar.
        EditText searchBar = findViewById(R.id.search_product);

        searchBar.setOnKeyListener((v, keyCode, event) -> {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                String productQuery = String.valueOf(searchBar.getText());
                searchBar.setText("");
            }
            return false;
        });
    }
}
