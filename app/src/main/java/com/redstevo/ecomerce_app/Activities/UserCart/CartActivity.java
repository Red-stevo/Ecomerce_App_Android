package com.redstevo.ecomerce_app.Activities.UserCart;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.R;

public class CartActivity extends GeneralActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
    }
}
