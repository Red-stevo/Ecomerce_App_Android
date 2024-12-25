package com.redstevo.ecomerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);


        /*Handle moving to the registration page.*/
        TextView textView = findViewById(R.id.register_link);
        textView.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(registerIntent);
        });

        /*Handle the login click*/

    }
}