package com.redstevo.ecomerce_app.Activities.GeneralView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.redstevo.ecomerce_app.Activities.Order.OrdersActivity;
import com.redstevo.ecomerce_app.Activities.UserCart.CartActivity;
import com.redstevo.ecomerce_app.Activities.profile.UserProfile;
import com.redstevo.ecomerce_app.R;

import lombok.Getter;

@Getter
abstract public class GeneralActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


        sharedPreferences = getSharedPreferences("dataUtils", MODE_PRIVATE);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void handleSearching(EditText editText) {
        editText.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN){
                String searchQuery = editText.getText().toString();
                sharedPreferences.edit().putString("query", searchQuery).apply();
            }
            return true;
        });
    }

    public void handleUserProfileClick(ImageView userProfile) {
        userProfile.setOnClickListener(event -> {
            startActivity(new Intent(this, UserProfile.class));
            finish();
        });
    }

    public void handleUserCartClick(ImageView userCart) {
        userCart.setOnClickListener(event -> {
            startActivity(new Intent(this, CartActivity.class));
            finish();
        });
    }

    public void handleTrackOrderClick(ImageView trackOrder) {
        trackOrder.setOnClickListener(event -> {
            startActivity(new Intent(this, OrdersActivity.class));
            finish();
        });
    }
}



