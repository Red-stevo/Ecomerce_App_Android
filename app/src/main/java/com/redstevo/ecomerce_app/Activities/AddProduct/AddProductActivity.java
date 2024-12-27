package com.redstevo.ecomerce_app.Activities.AddProduct;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redstevo.ecomerce_app.Adapters.ImageVideoPreviewAdapter;
import com.redstevo.ecomerce_app.Models.ImagePreviewModel;
import com.redstevo.ecomerce_app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private static final List<ImagePreviewModel> imagePreviewModelList = new ArrayList<>();


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
                            ImagePreviewModel imagePreviewModel = new ImagePreviewModel();
                            imagePreviewModel.setImageVideoUri(data.getData());
                            imagePreviewModelList.add(imagePreviewModel);

                            Log.d("Upload", "onCreate: data"+imagePreviewModel);

                            RecyclerView recyclerView = findViewById(R.id.preview_image_and_videos);
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(
                                    new ImageVideoPreviewAdapter(imagePreviewModelList, this));
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