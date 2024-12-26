package com.redstevo.ecomerce_app.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.redstevo.ecomerce_app.R;

public class AddProductActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        /* Handle addition of video or image */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(AddProductActivity.this, requestCode, Toast.LENGTH_LONG).show();

        System.out.println("The Image Data"+data);
        assert data != null;
        Uri uploadedData = data.getData();

        Toast.makeText(AddProductActivity.this, "The Image URI Data"+String.valueOf(uploadedData), Toast.LENGTH_LONG).show();
    }

}