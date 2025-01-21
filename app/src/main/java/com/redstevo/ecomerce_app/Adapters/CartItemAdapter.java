package com.redstevo.ecomerce_app.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

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

    private Context context;

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        CartItemModel cartItemModel = cartItemModelList.get(position);

        Picasso
                .get()
                .load(cartItemModel.getImageUrl())
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.image_not_found)
                .into(holder.imageUrl);

        holder.productPrice.setText("Ksh "+cartItemModel.getProductPrice());
        holder.productName.setText(cartItemModel.getProductName());
        holder.productQuantity.setText(String.valueOf(cartItemModel.getProductQuantity()));


        handleMinusQuantity(holder.minusQuantity, holder.productQuantity, cartItemModel);
        handlePlusQuantity(holder.plusQuantity, holder.productQuantity, cartItemModel);
        handleQuantityUpdate(holder.productQuantity, cartItemModel);
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    private void handlePlusQuantity(
            TextView plusQuantity, EditText quantityInput, CartItemModel cartItemModel){
        plusQuantity.setOnClickListener(event -> {
            cartItemModel.setProductQuantity(cartItemModel.getProductQuantity() + 1);
            quantityInput.setText(String.valueOf(cartItemModel.getProductQuantity()));
        });
    }

    private void handleMinusQuantity(
            TextView minusQuantity, EditText quantityInput, CartItemModel cartItemModel){
        minusQuantity.setOnClickListener(event -> {

            if (cartItemModel.getProductQuantity() >= 2)
                cartItemModel.setProductQuantity(cartItemModel.getProductQuantity() - 1);
            else
                cartItemModel.setProductQuantity(1);
            quantityInput.setText(String.valueOf(cartItemModel.getProductQuantity()));
        });
    }

    private void handleQuantityUpdate(EditText editText, CartItemModel cartItemModel){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!editText.getText().toString().isEmpty()){
                    if (Integer.parseInt(String.valueOf(editText.getText())) >= 1){
                        cartItemModel.setProductQuantity(
                                Integer.parseInt(String.valueOf(editText.getText())));
                    }else {
                        cartItemModel.setProductQuantity(1);
                        editText.setText(String.valueOf(1));
                    }
                }
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

        private LinearLayout layout;



        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.imageUrl = itemView.findViewById(R.id.cart_item_image);
            this.productName = itemView.findViewById(R.id.cart_item_name);
            this.productQuantity = itemView.findViewById(R.id.cart_item_quantity);
            this.productPrice = itemView.findViewById(R.id.cart_item_price);
            this.minusQuantity = itemView.findViewById(R.id.cart_minus_quantity);
            this.plusQuantity = itemView.findViewById(R.id.cart_plus_quantity);
            this.layout = itemView.findViewById(R.id.cart_item_view);

        }
    }
}
