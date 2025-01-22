package com.redstevo.ecomerce_app.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Models.InventoryItem;
import com.redstevo.ecomerce_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private final List<InventoryItem> inventoryList;

    public InventoryAdapter(List<InventoryItem> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryItem item = inventoryList.get(position);
        holder.productName.setText(item.getName());
        holder.productCount.setText(String.valueOf(item.getCount()));
        holder.productPrice.setText(String.format("$%.2f", item.getPrice()));
        Picasso.get()
                .load(item.getImageUrl())
                /*.placeholder(R.id.image_preview_field)
                .error()*/
                .into(holder.inImage);
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productCount, productPrice;
        ImageView inImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tvProductName);
            productCount = itemView.findViewById(R.id.tvProductCount);
            productPrice = itemView.findViewById(R.id.tvProductPrice);
            inImage = itemView.findViewById(R.id.inImage);
        }
    }
}
