package com.redstevo.ecomerce_app.Activities.SingleProduct;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Activities.Order.OrderNowActivity;
import com.redstevo.ecomerce_app.R;
import com.redstevo.ecomerce_app.Services.GetProduct;
import com.redstevo.ecomerce_app.Services.MeiliSearchService;

public class singleProductViewActivity extends AppCompatActivity {

    private final GetProduct getProduct;

    private final MeiliSearchService meiliSearchService;

    private SharedPreferences sharedPreferences;

    public singleProductViewActivity() {
        this.getProduct = new GetProduct();
        this.meiliSearchService = new MeiliSearchService();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_view);
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");
        sharedPreferences = super.getSharedPreferences("dataUtils", MODE_PRIVATE);


        //setting even listeners.
        findViewById(R.id.order_now_btn).setOnClickListener(view -> {
            startActivity(new Intent(this, OrderNowActivity.class));
        });


        ImageView productImageView = findViewById(R.id.single_product_image_view);
        RecyclerView selectImageView = findViewById(R.id.product_selection_view);
        TextView productTitleView = findViewById(R.id.product_title_view);
        TextView productDescriptionView = findViewById(R.id.product_description_view);
        TextView productPriceView = findViewById(R.id.price);
        TextView productDiscountPercentageView = findViewById(R.id.discount);
        TextView productCountView = findViewById(R.id.singleProductViewCount);
        Button addCartButton = findViewById(R.id.add_to_cart_btn);
        RecyclerView productReviewView = findViewById(R.id.product_reviews_section);
        RecyclerView productRelatedProductsView = findViewById(R.id.related_products_view);

        getProduct.getProductByKey(productId,this, productImageView,
                productTitleView, selectImageView, productDescriptionView, productCountView,
                productPriceView, productDiscountPercentageView, addCartButton);

        meiliSearchService.searchProducts(sharedPreferences.getString("query", " "),
               this, productRelatedProductsView);
    }
}
