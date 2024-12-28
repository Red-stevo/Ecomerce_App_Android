package com.redstevo.ecomerce_app.Activities.AddProduct;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.redstevo.ecomerce_app.Adapters.ImageVideoPreviewAdapter;
import com.redstevo.ecomerce_app.Models.ImagePreviewModel;
import com.redstevo.ecomerce_app.Models.NewProductModel;
import com.redstevo.ecomerce_app.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddProductActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;

    private final List<NewProductModel> newProductModelList;

    private int currentProduct;


    public AddProductActivity() {
        newProductModelList = new ArrayList<>();
        currentProduct = -1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        List<ImagePreviewModel> imagePreviewModelList = new ArrayList<>();
        List<Bitmap> productBitmapData = new ArrayList<>();


        EditText productTitle = findViewById(R.id.product_title);
        EditText productDescription = findViewById(R.id.product_description);
        EditText productPrice = findViewById(R.id.product_price);
        EditText productDiscount = findViewById(R.id.product_discount);
        EditText productCount = findViewById(R.id.product_count);
        Button previousButton = findViewById(R.id.previous_btn);
        Button nextButton = findViewById(R.id.next_btn);
        Button saveAll = findViewById(R.id.save_products);



/*Handle Previous Button*/
    previousButton.setOnClickListener(view -> {

        /*Check if the there is previous*/
        if (newProductModelList.isEmpty() || currentProduct == 0 ){
            Toast.makeText(this, "No Previous Product"+ currentProduct, Toast.LENGTH_SHORT).show();
            return;
        }

        /*Check if the field is empty*/
        List<EditText> editTexts = new ArrayList<>(List.of(productTitle, productDescription,
                productPrice, productDiscount, productCount));

        if (checkEmptyFields(editTexts, imagePreviewModelList)) return;

        newProductModelList.set(currentProduct, new NewProductModel(
                productTitle.getText().toString(), productDescription.getText().toString(),
                Float.parseFloat(productPrice.getText().toString()),
                Float.parseFloat(productDiscount.getText().toString()),
                Integer.parseInt(productCount.getText().toString()), productBitmapData,
                imagePreviewModelList));


        --currentProduct;
        repopulateProductInputFields(productTitle, productDescription,
                productPrice, productDiscount, productCount, imagePreviewModelList,
                productBitmapData);
    });



    /*Handle next Button*/
    nextButton.setOnClickListener(view -> {

        if (currentProduct == newProductModelList.size()){
            Toast.makeText(this, "No Next Product", Toast.LENGTH_SHORT).show();
            return;
        }

        if(currentProduct == -1) currentProduct = newProductModelList.size() - 1;

        /*Check if the field is empty*/
        List<EditText> editTexts = new ArrayList<>(List.of(productTitle, productDescription,
                productPrice, productDiscount, productCount));

        if (checkEmptyFields(editTexts, imagePreviewModelList)) return;

        if (currentProduct == newProductModelList.size() -1) {

            newProductModelList.add(new NewProductModel(
                    productTitle.getText().toString(), productDescription.getText().toString(),
                    Float.parseFloat(productPrice.getText().toString()),
                    Float.parseFloat(productDiscount.getText().toString()),
                    Integer.parseInt(productCount.getText().toString()), productBitmapData,
                    imagePreviewModelList));

            //clear the input fields
            clearFields(editTexts, productBitmapData, imagePreviewModelList);

            /*Clear images preview */
            populateRecycleView(imagePreviewModelList);

            ++currentProduct;

        } else {
            newProductModelList.set(currentProduct, new NewProductModel(
                    productTitle.getText().toString(), productDescription.getText().toString(),
                    Float.parseFloat(productPrice.getText().toString()),
                    Float.parseFloat(productDiscount.getText().toString()),
                    Integer.parseInt(productCount.getText().toString()), productBitmapData,
                    imagePreviewModelList));

            ++currentProduct;

            repopulateProductInputFields(productTitle, productDescription, productPrice,
                    productDiscount, productCount, imagePreviewModelList, productBitmapData);
        }

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

                            populateRecycleView(imagePreviewModelList);

                            try {
                                productBitmapData.add(
                                        MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                        Uri.parse(Objects.requireNonNull(data.getData()).toString())));
                            } catch (IOException e) {
                                imagePreviewModelList.remove(imagePreviewModelList.size() - 1);
                                Toast.makeText(this, "Could Fetch The Image",
                                        Toast.LENGTH_LONG + 1).show();
                            }
                        }

                    } else
                        Toast.makeText(this, "Operation failed or canceled",
                                Toast.LENGTH_SHORT).show();
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

    private void repopulateProductInputFields(
            EditText productTitle, EditText productDescription, EditText productPrice,
            EditText productDiscount, EditText productCount, List<ImagePreviewModel>
                    imagePreviewModelList, List<Bitmap> productBitmapData) {

        NewProductModel currentNewProductModel =  newProductModelList.get(currentProduct);

        /*Set current product data to view.*/
        productTitle.setText(currentNewProductModel.getProductName());
        productDescription.setText(currentNewProductModel.getProductDescription());
        productPrice.setText(String.valueOf(currentNewProductModel.getProductPrice()));
        productDiscount.setText(String.valueOf(currentNewProductModel.getProductDiscount()));
        productCount.setText(String.valueOf(currentNewProductModel.getProductCount()));
        imagePreviewModelList.clear();
        imagePreviewModelList.addAll(currentNewProductModel.getProductImagesUri());
        productBitmapData.clear();
        productBitmapData.addAll(currentNewProductModel.getProductImages());

        Toast.makeText(this, imagePreviewModelList.toString(), Toast.LENGTH_SHORT).show();

        populateRecycleView(imagePreviewModelList);
    }

    private boolean checkEmptyFields(List<EditText> editTexts, List<ImagePreviewModel> imagePreviewModelList) {

        // Load the custom shape drawable from the XML file
        Drawable redBorderBackground=AppCompatResources.getDrawable(AddProductActivity.this,
                R.drawable.red_border);
        Drawable blackBorderBackground=AppCompatResources.getDrawable(AddProductActivity.this,
                R.drawable. black_border);


        for (EditText editText : editTexts){
            if(editText.getText().toString().isEmpty()){
                editText.setBackground(redBorderBackground);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        editText.setBackground(blackBorderBackground);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                return true;
            }
        }

        if (imagePreviewModelList.isEmpty()){
            Toast.makeText(this, "At Least One Image Is Required.",
                    Toast.LENGTH_LONG + 1).show();
            return true;
        }
        return false;
    }

    private void clearFields(List<EditText> editTextList, List<Bitmap> productBitmapData,
            List<ImagePreviewModel> imagePreviewModelList) {

        /*Clear the field.*/
        for (EditText editText : editTextList) editText.setText("");

        productBitmapData.clear();
        imagePreviewModelList.clear();
    }

    private void populateRecycleView(List<ImagePreviewModel> imagePreviewModelList) {
        RecyclerView recyclerView = findViewById(R.id.preview_image_and_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ImageVideoPreviewAdapter(imagePreviewModelList, this));
    }
}