package com.redstevo.ecomerce_app.Activities.GeneralView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.redstevo.ecomerce_app.R;

import lombok.Getter;

@Getter
abstract public class GeneralActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("dataUtils", MODE_PRIVATE);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_general);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageView trackOrder = findViewById(R.id.track_order);
        trackOrder.setOnClickListener(event -> {

        });
    }

    public void handleSearching(EditText editText) {
        editText.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN){
                String searchQuery = editText.getText().toString();
                Log.d("SEARCH_BAR", "onKey: "+searchQuery);
                sharedPreferences.edit().putString("query", searchQuery).apply();
            }
            return true;
        });
    }

    public void handleUserProfileClick(ImageView userProfile) {
        userProfile.setOnClickListener(event -> {

        });
    }

    public void handleUserCartClick(ImageView userCart) {
        userCart.setOnClickListener(event -> {

        });
    }

    public void handleTrackOrderClick(ImageView trackOrder) {
        trackOrder.setOnClickListener(event -> {

        });
    }
}



