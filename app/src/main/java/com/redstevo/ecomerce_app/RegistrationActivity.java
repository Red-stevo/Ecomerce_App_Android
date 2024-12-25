package com.redstevo.ecomerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /*handle the login page link*/
        TextView textView = findViewById(R.id.login_page_link);
        textView.setOnClickListener(view -> {
            Intent loginPageIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(loginPageIntent);
        });
    }
}