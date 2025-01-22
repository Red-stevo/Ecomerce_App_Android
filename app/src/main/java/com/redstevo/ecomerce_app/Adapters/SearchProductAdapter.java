package com.redstevo.ecomerce_app.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Models.ProductModel;
import com.redstevo.ecomerce_app.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SearchProductAdapter
        extends RecyclerView.Adapter<SearchProductAdapter.SearchProductsHolder> {

    private List<ProductModel> searchProductModelList;


    @NonNull
    @Override
    public SearchProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchProductsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_display, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProductsHolder holder, int position) {
        StringBuilder priceString = new StringBuilder();
        StringBuilder discount = new StringBuilder();
        ProductModel searchProductModel = searchProductModelList.get(position);
        String imageUrl = searchProductModel.getProductUrls().get(0).replace("[", "").replace("]", "");
        Picasso
                .get()
                .load(imageUrl)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.image_not_found)
                .into(holder.getProductImageView());

        holder.getProductNameView().setText(searchProductModel.getProductName());
        priceString.append("KES ");
        priceString.append(searchProductModel.getProductPrice());
        holder.getProductPriceView().setText(priceString);
        discount.append(new DecimalFormat("#.00").format((searchProductModel.getProductDiscount() / searchProductModel.getProductPrice()) * 100));
        discount.append("% OFF");
        holder.getProductDiscountPercentView().setText(discount);

       ;


    }

    @Override
    public int getItemCount() {
        return searchProductModelList.size();
    }



    @Getter
    public static class SearchProductsHolder extends RecyclerView.ViewHolder {

        private final ImageView productImageView;

        private final TextView productNameView;

        private final TextView productPriceView;

        private final TextView productDiscountPercentView;

        public SearchProductsHolder(@NonNull View itemView) {
            super(itemView);

            this.productImageView = itemView.findViewById(R.id.product_display_image);
            this.productNameView = itemView.findViewById(R.id.product_display_name);
            this.productPriceView = itemView.findViewById(R.id.product_price_view);
            this.productDiscountPercentView = itemView
                    .findViewById(R.id.product_discount_percent_view);
        }
    }
}
