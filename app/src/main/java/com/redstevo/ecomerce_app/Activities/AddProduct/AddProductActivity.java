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
import com.redstevo.ecomerce_app.Activities.Login.LoginActivity;
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

    private List<NewProductModel> newProductModels;
    private List<ImagePreviewModel> imagePreviewModels;

    private List<Bitmap> imageBitmapData;

    private int currentProductView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        imagePreviewModels = new ArrayList<>();
        imageBitmapData = new ArrayList<>();
        currentProductView = -1;
        newProductModels = new ArrayList<>();


        EditText productTitle = findViewById(R.id.product_title);
        EditText productDescription = findViewById(R.id.product_description);
        EditText productPrice = findViewById(R.id.product_price);
        EditText productDiscount = findViewById(R.id.product_discount);
        EditText productCount = findViewById(R.id.product_count);
        Button previousButton = findViewById(R.id.previous_btn);
        Button nextButton = findViewById(R.id.next_btn);
        Button saveAll = findViewById(R.id.save_products);


        // Handle addition of video or image
        FloatingActionButton uploadImageVideo = findViewById(R.id.upload_image_and_videos);
        uploadImageVideo.setOnClickListener(event -> {
            Intent uploadIntent = new Intent(Intent.ACTION_PICK);
            uploadIntent.setType("*/*");
            String[] mimeTypes = {"video/*", "image/*"};
            uploadIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            activityResultLauncher.launch(uploadIntent);

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

                            imagePreviewModels.add(imagePreviewModel);
                            populateRecycleView(imagePreviewModels);

                            try {
                                imageBitmapData.add(
                                        MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                                Uri.parse(Objects.requireNonNull(data.getData()).toString())));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }

                    } else
                        Toast.makeText(this, "Operation failed or canceled",
                                Toast.LENGTH_SHORT).show();
                }
        );


        /*Handle next Button*/


        /*Save the current view to the next products Model*/
        nextButton.setOnClickListener(view -> {


            String productTitleValue = String.valueOf(productTitle.getText());
            String productDescriptionValue = String.valueOf(productDescription.getText());
            Float productPriceValue = Float.parseFloat(String.valueOf(productPrice.getText()));
            Float productDiscountValue = Float.parseFloat(String.valueOf(productDiscount.getText()));
            Integer productCountValue = Integer.parseInt(String.valueOf(productCount.getText()));


            if (currentProductView == -1 || currentProductView == newProductModels.size()){
                NewProductModel newProductModel = new NewProductModel(
                        productTitleValue, productDescriptionValue,productPriceValue,
                        productDiscountValue,productCountValue, imageBitmapData, imagePreviewModels
                );

                newProductModels.add(newProductModel);

                clearFields(List.of(
                        productTitle,productDescription,productPrice,productDiscount,productCount));

                imageBitmapData = new ArrayList<>();
                imagePreviewModels = new ArrayList<>();

                populateRecycleView(imagePreviewModels);
            }else {
                NewProductModel newProductModel = newProductModels.get(currentProductView);
                newProductModel.setProductName(productTitleValue);
                newProductModel.setProductDescription(productDescriptionValue);
                newProductModel.setProductPrice(productPriceValue);
                newProductModel.setProductDiscount(productDiscountValue);
                newProductModel.setProductCount(productCountValue);
                newProductModel.setProductImagesUri(imagePreviewModels);
                newProductModel.setProductImages(imageBitmapData);

                //move to the next field.
                currentProductView +=1;
                NewProductModel currentProductModel = newProductModels.get(currentProductView);
                repopulateField(productTitle, productDescription, productPrice, productDiscount,
                        productCount, currentProductModel);

            }

        });


        /*Handle Previous Button*/
        previousButton.setOnClickListener(view -> {
            currentProductView = 0;
        });

    }

    private void repopulateField(EditText title, EditText description, EditText price,
                                 EditText discount, EditText count, NewProductModel newProductModel){

        populateRecycleView(newProductModel.getProductImagesUri());
        title.setText(newProductModel.getProductName());
        description.setText(newProductModel.getProductDescription());
        price.setText(String.valueOf(newProductModel.getProductPrice()));
        discount.setText(String.valueOf(newProductModel.getProductDiscount()));
        count.setText(String.valueOf(newProductModel.getProductCount()));

    }


    private boolean checkEmptyFields(
            List<EditText> editTexts, List<ImagePreviewModel> imagePreviewModelList) {

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

    private void clearFields(List<EditText> editTextList) {
        /*Clear the field.*/
        for (EditText editText : editTextList) editText.setText("");
    }

    private void populateRecycleView(List<ImagePreviewModel> imagePreviewModelList) {
        RecyclerView recyclerView = findViewById(R.id.preview_image_and_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ImageVideoPreviewAdapter(imagePreviewModelList, this));
    }
}