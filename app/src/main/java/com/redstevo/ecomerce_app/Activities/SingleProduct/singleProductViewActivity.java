package com.redstevo.ecomerce_app.Activities.SingleProduct;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.R;

public class singleProductViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_view);

        ImageView productImageView = findViewById(R.id.single_product_image_view);
        RecyclerView selectImageView = findViewById(R.id.product_selection_view);
        TextView productTitleView = findViewById(R.id.product_title_view);
        TextView productDescriptionView = findViewById(R.id.product_description_view);
        Button orderNowButton = findViewById(R.id.order_now_btn);
        Button addCartButton = findViewById(R.id.add_to_cart_btn);
        RecyclerView productReviewView = findViewById(R.id.product_reviews_section);
        RecyclerView productRelatedProductsView = findViewById(R.id.related_products_view);
    }
}
