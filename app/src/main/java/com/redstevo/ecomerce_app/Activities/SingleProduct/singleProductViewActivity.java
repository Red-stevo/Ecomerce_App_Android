package com.redstevo.ecomerce_app.Activities.SingleProduct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.redstevo.ecomerce_app.Activities.Order.OrderNowActivity;
import com.redstevo.ecomerce_app.Adapters.ProductImageAdapter;
import com.redstevo.ecomerce_app.Models.CartItemModel;
import com.redstevo.ecomerce_app.Models.ProductDetailsModel;
import com.redstevo.ecomerce_app.R;
import com.redstevo.ecomerce_app.Services.GetProduct;

import java.text.DecimalFormat;
import java.util.List;

public class singleProductViewActivity extends AppCompatActivity {

    private final GetProduct getProduct;

    private final DatabaseReference reference;

    public singleProductViewActivity() {
        this.getProduct = new GetProduct();
        reference = FirebaseDatabase
                .getInstance("https://myapplication-fce0cb20-default-rtdb.firebaseio.com/")
                .getReference();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_view);
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");


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
        TextView productCountView = findViewById(R.id.count);
        Button addCartButton = findViewById(R.id.add_to_cart_btn);
        RecyclerView productReviewView = findViewById(R.id.product_reviews_section);
        RecyclerView productRelatedProductsView = findViewById(R.id.related_products_view);

        populateProductDetails(productId, productImageView, productTitleView, selectImageView,
                productDescriptionView, productCountView, productPriceView,
                productDiscountPercentageView, addCartButton);
    }

    private void populateProductDetails(
            String productId, ImageView productImageView, TextView productTitleView,
            RecyclerView selectImageView, TextView productDescriptionView, TextView productCountView,
            TextView productPriceView, TextView productDiscountPercentageView, Button addCart) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

        ProductDetailsModel detailsModel = getProduct.getProductByKey(productId);

        if (detailsModel == null){
            detailsModel = new ProductDetailsModel();
        }

        detailsModel.setProductCount(20);
        detailsModel.setProductDiscount(230.50F);
        detailsModel.setProductPrice(3500.00F);
        detailsModel.setProductDescription("The Best children clothes in the market.Durable and" +
                " fast to clean.Your child will never be more beautiful, come visit us today" +
                "and find out how much more she could be.");
        detailsModel.setProductName("Children Dresses");

        detailsModel.setProductImagesUrls(List.of(
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.g9ziyrYoAGUtig18K-k3pgHaHa%26pid%3DApi&f=1&ipt=88270b045421afa7f47ac303203c985e07d488400172626d0fec18565d24e8eb&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.elPqPSN0p8F7zIerDBRSUQHaHa%26pid%3DApi&f=1&ipt=87b353ef470684e9fb4d24dedc5acba849256818cbcd7003882359396547239b&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.K8qhS0TCefDe6l6Ke7HZeAHaHa%26pid%3DApi&f=1&ipt=e93d742f8d486f72a61586c55b8ed2c1b20974d84b78837465d13541af092ce0&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.tRyXg87M_e6VAgogaPfBwgHaHa%26pid%3DApi&f=1&ipt=e3e80d6b8e19bcc52007fd03d02efa574f0c54b4abb19cebe7db824eab60cdbb&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.nz2LhHdjpEVNMAAA6oGh6QHaHa%26pid%3DApi&f=1&ipt=a7c13f2faa1b70d25fe9139c7710f158ea427d07bdf770eab8afe151538284f8&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.3uaK00PL19Pym8cxWX4oSQHaHa%26pid%3DApi&f=1&ipt=c96689ef47cf494b8de5844238af540b883ecf37093b68f5eec86f2e023a076d&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.r8HA9M6DubccuiR1IoC-9wHaIP%26pid%3DApi&f=1&ipt=18cfd3121d54b3307718b4b829d081fd18313c07d9ad2cdf41de0df806da947a&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.73Yndv8io3A-JSgj48sFhgHaHa%26pid%3DApi&f=1&ipt=b38fbfc64716d2a8ae9f7763b69eff0c05d7d69ed5c2bec90339d2a2c2c72753&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP.p8ybcMhBkrHbJVUgsIVqyQHaHa%26pid%3DApi&f=1&ipt=fb8c7fa333b961a4aa3287ed485091604c2cbe0a8ab5fb75071bc90164857680&ipo=images",
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse2.mm.bing.net%2Fth%3Fid%3DOIP._tpWbnSsHb5CO-ZmX00exAHaE8%26pid%3DApi&f=1&ipt=54c5945f8880f95501fa53542dac649d2fb133e56a45cb76b0f23a4bdbd12b47&ipo=images"
        ));
        progressDialog.hide();

        ProductImageAdapter productImageAdapter = new ProductImageAdapter(
                this, productImageView,detailsModel.getProductImagesUrls());
        selectImageView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        selectImageView.setHasFixedSize(false);
        selectImageView.setAdapter(productImageAdapter);

        productTitleView.setText(detailsModel.getProductName());
        productDescriptionView.setText(detailsModel.getProductDescription());
        productPriceView.setText(String.valueOf("KSH "+detailsModel.getProductPrice()));
        productCountView.setText(String.valueOf(detailsModel.getProductCount()));
        productDiscountPercentageView.setText(String.valueOf(
                new DecimalFormat("#.00").format((
                detailsModel.getProductDiscount())/ detailsModel.getProductPrice() * 100) +" % OFF"));


        ProductDetailsModel finalDetailsModel = detailsModel;
        addCart.setOnClickListener(view -> {
            String userId = "AAA";
            String key  = reference.child("cart"+userId).push().getKey();

            if (key != null) {
                reference.child(key)
                        .setValue(new CartItemModel(finalDetailsModel.getProductImagesUrls().get(0),
                                finalDetailsModel.getProductName(), 1,
                                finalDetailsModel.getProductPrice()))
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }).addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Product Added To Cart", Toast.LENGTH_SHORT)
                                    .show();
                        });
            }
        });
    }
}
