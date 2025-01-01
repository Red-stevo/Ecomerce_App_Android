package com.redstevo.ecomerce_app.Adapters;

import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redstevo.ecomerce_app.Models.CartItemModel;
import com.redstevo.ecomerce_app.R;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ItemHolder> {

    private List<CartItemModel> cartItemModelList;

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        CartItemModel cartItemModel = cartItemModelList.get(position);
        //holder.imageUrl.setImageURI(Uri.parse(cartItemModel.getImageUrl()));
        holder.productPrice.setText("Ksh "+cartItemModel.getProductPrice());
        holder.productName.setText(cartItemModel.getProductName());
        holder.productQuantity.setText(String.valueOf(cartItemModel.getProductQuantity()));

        handleMinusQuantity(holder.minusQuantity, cartItemModel);
        handlePlusQuantity(holder.plusQuantity, cartItemModel);
        handleQuantityUpdate(holder.productQuantity, cartItemModel);
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    private void handlePlusQuantity(TextView plusQuantity, CartItemModel cartItemModel){
        plusQuantity.setOnClickListener((event) -> {
            cartItemModel.setProductQuantity(cartItemModel.getProductQuantity() + 1);
        });
    }

    private void handleMinusQuantity(TextView minusQuantity, CartItemModel cartItemModel){
        minusQuantity.setOnClickListener((event) -> {

            if (cartItemModel.getProductQuantity() >= 2)
                cartItemModel.setProductQuantity(cartItemModel.getProductQuantity() - 1);
            else
                cartItemModel.setProductQuantity(1);
        });
    }

    private void handleQuantityUpdate(EditText editText, CartItemModel cartItemModel){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cartItemModel.setProductQuantity(Math.max(Integer.parseInt(String.valueOf(s)), 1));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Setter
    @Getter
    public static class ItemHolder extends RecyclerView.ViewHolder {

        private ImageView imageUrl;

        private TextView productName;

        private EditText productQuantity;

        private TextView productPrice;

        private TextView minusQuantity;

        private TextView plusQuantity;



        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.imageUrl = itemView.findViewById(R.id.cart_item_image);
            this.productName = itemView.findViewById(R.id.cart_item_name);
            this.productQuantity = itemView.findViewById(R.id.cart_item_quantity);
            this.productPrice = itemView.findViewById(R.id.cart_item_price);
            this.minusQuantity = itemView.findViewById(R.id.cart_minus_quantity);
            this.plusQuantity = itemView.findViewById(R.id.cart_plus_quantity);

        }
    }
}
