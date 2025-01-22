package com.redstevo.ecomerce_app.Activities.Order;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        String userId = getIntent().getStringExtra("USER_ID"); // Get the userId from Intent


    }
}
