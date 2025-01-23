package com.redstevo.ecomerce_app.Activities.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Activities.Login.LoginActivity;
import com.redstevo.ecomerce_app.Activities.Order.OrdersActivity;
import com.redstevo.ecomerce_app.Activities.UserCart.CartActivity;
import com.redstevo.ecomerce_app.R;

public class UserProfile extends GeneralActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        if (user != null) {
            String email = user.getEmail();
            TextView emailTextView = findViewById(R.id.tvUserEmail);
            emailTextView.setText(email);
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
        }


        TextView logoutButton = findViewById(R.id.tvLogout);
        logoutButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(UserProfile.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        ImageView ordersImage = findViewById(R.id.orders_image);
        ordersImage.setOnClickListener(v->{
            assert user != null;
            String userId = user.getUid();
            Intent intent = new Intent(UserProfile.this, OrdersActivity.class);
            intent.putExtra("USER_ID",userId);
            startActivity(intent);


        });




        ImageView cartImage = findViewById(R.id.cart_image);
        cartImage.setOnClickListener(v->{
            assert user != null;
            String userId  = user.getUid();
            Intent cartIntent = new Intent(UserProfile.this, CartActivity.class);
            cartIntent.putExtra("USER_ID",userId);
            startActivity(cartIntent);
        });



    }
}
