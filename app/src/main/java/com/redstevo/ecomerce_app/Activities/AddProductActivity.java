package com.redstevo.ecomerce_app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redstevo.ecomerce_app.R;

import java.net.URL;

public class AddProductActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        /* Handle addition of video or image */
        FloatingActionButton addImageVideo = findViewById(R.id.handleAddImageVideo);
        addImageVideo.setOnClickListener(event -> {
            Intent uploadIntent = new Intent(Intent.ACTION_GET_CONTENT);
            uploadIntent.setType("image/** | video/**");
            uploadIntent.putExtra(Intent.EXTRA_QUIET_MODE, Activity.RESULT_OK);
            startActivityForResult(uploadIntent, RESULT_OK);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(AddProductActivity.this, requestCode, Toast.LENGTH_LONG).show();

        assert data != null;
        Uri uploadedData = data.getData();

        Toast.makeText(AddProductActivity.this, String.valueOf(uploadedData), Toast.LENGTH_LONG).show();
    }

}