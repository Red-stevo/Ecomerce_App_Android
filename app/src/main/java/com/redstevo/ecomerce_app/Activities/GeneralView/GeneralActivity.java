package com.redstevo.ecomerce_app.Activities.GeneralView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.redstevo.ecomerce_app.R;

import java.util.Objects;

public class GeneralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView userProfile = findViewById(R.id.user_profile);
        userProfile.setOnClickListener(event -> {

        });

        ImageView userCart = findViewById(R.id.user_cart);
        userCart.setOnClickListener(event -> {

        });

        ImageView trackOrder = findViewById(R.id.track_order);
        trackOrder.setOnClickListener(event -> {

        });

        EditText editText = findViewById(R.id.search_product);
        editText.setOnKeyListener((v, keyCode, event) -> {
           if(event.getAction() == KeyEvent.ACTION_DOWN){
                String searchQuery = editText.getText().toString();
               Log.d("SEARCH_BAR", "onKey: "+searchQuery);
            }
            return true;
        });
    }
}