package com.redstevo.ecomerce_app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redstevo.ecomerce_app.R;

public class AddProductActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        // Initialize the ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri selectedUri = data.getData();  // Get the selected file URI
                            // Handle the selected image or video file
                            Log.d("ActivityResult", "Selected file URI: " + selectedUri);
                            // You can now process the file, display it, or upload it
                        }
                    } else {
                        Log.d("ActivityResult", "Operation failed or canceled");
                    }
                }
        );

        // Handle addition of video or image
        FloatingActionButton uploadImageVideo = findViewById(R.id.upload_image_and_videos);
        uploadImageVideo.setOnClickListener(event -> {
            Intent uploadIntent = new Intent(Intent.ACTION_PICK);
            uploadIntent.setType("*/*");
            String[] mimeTypes = {"video/*", "image/*"};
            uploadIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            activityResultLauncher.launch(uploadIntent);
        });
    }


}