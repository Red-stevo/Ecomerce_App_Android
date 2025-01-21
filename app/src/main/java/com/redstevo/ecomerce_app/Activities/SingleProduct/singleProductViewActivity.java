package com.redstevo.ecomerce_app.Activities.SingleProduct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Models.ProductDetailsModel;
import com.redstevo.ecomerce_app.R;
import com.redstevo.ecomerce_app.Services.GetProduct;

import java.text.DecimalFormat;

public class singleProductViewActivity extends AppCompatActivity {

    private final GetProduct getProduct;

    public singleProductViewActivity(GetProduct getProduct) {
        this.getProduct = getProduct;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_view);
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");

        ImageView productImageView = findViewById(R.id.single_product_image_view);
        RecyclerView selectImageView = findViewById(R.id.product_selection_view);
        TextView productTitleView = findViewById(R.id.product_title_view);
        TextView productDescriptionView = findViewById(R.id.product_description_view);
        TextView productPriceView = findViewById(R.id.price);
        TextView productDiscountPercentageView = findViewById(R.id.discount);
        TextView productCountView = findViewById(R.id.count);
        Button orderNowButton = findViewById(R.id.order_now_btn);
        Button addCartButton = findViewById(R.id.add_to_cart_btn);
        RecyclerView productReviewView = findViewById(R.id.product_reviews_section);
        RecyclerView productRelatedProductsView = findViewById(R.id.related_products_view);

        populateProductDetails(productId, productImageView, productTitleView, selectImageView,
                productDescriptionView, productCountView, productPriceView, productDiscountPercentageView);
    }

    private void populateProductDetails(
            String productId, ImageView productImageView, TextView productTitleView,
            RecyclerView selectImageView, TextView productDescriptionView, TextView productCountView,
            TextView productPriceView, TextView productDiscountPercentageView) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        ProductDetailsModel detailsModel = getProduct.getProductByKey(productId);
        progressDialog.hide();



        productTitleView.setText(detailsModel.getProductName());
        productDescriptionView.setText(detailsModel.getProductDescription());
        productPriceView.setText(String.valueOf("KSH "+detailsModel.getProductPrice()));
        productCountView.setText(detailsModel.getProductCount());
        productDiscountPercentageView.setText(String.valueOf(
                new DecimalFormat("#.00").format((detailsModel.getProductPrice()-
                detailsModel.getProductDiscount())/ detailsModel.getProductPrice() * 100) +" %"));
    }
}
