package com.redstevo.ecomerce_app.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redstevo.ecomerce_app.R;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        /* Handle addition of video or image */
        FloatingActionButton addImageVideo = findViewById(R.id.handleAddImageVideo);
        addImageVideo.setOnClickListener(event -> {

        });
    }
}