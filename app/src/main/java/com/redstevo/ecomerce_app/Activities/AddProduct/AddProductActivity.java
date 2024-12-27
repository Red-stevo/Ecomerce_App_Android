package com.redstevo.ecomerce_app.Activities.AddProduct;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redstevo.ecomerce_app.Adapters.ImageVideoPreviewAdapter;
import com.redstevo.ecomerce_app.Models.ImagePreviewModel;
import com.redstevo.ecomerce_app.R;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddProductActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        List<ImagePreviewModel> imagePreviewModelList = new ArrayList<>();

        EditText productTitle = findViewById(R.id.product_title);
        EditText productDescription = findViewById(R.id.product_description);
        EditText productPrice = findViewById(R.id.product_price);
        EditText productDiscount = findViewById(R.id.product_discount);
        EditText productCount = findViewById(R.id.product_count);
        Button previousButton = findViewById(R.id.previous_btn);
        Button nextButton = findViewById(R.id.next_btn);
        Button saveAll = findViewById(R.id.save_products);


        previousButton.setOnClickListener(view -> {

        });

        nextButton.setOnClickListener(view -> {



        });


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

                            RecyclerView recyclerView = findViewById(R.id.preview_image_and_videos);
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setAdapter(
                                    new ImageVideoPreviewAdapter(imagePreviewModelList, this));


                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                        this.getContentResolver(),
                                        Uri.parse(Objects.requireNonNull(data.getData()).toString()));
                            } catch (IOException e) {
                                imagePreviewModelList.remove(imagePreviewModelList.size() - 1);
                                Toast.makeText(this, "Could Fetch The Image", Toast.LENGTH_LONG + 1).show();
                            }

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