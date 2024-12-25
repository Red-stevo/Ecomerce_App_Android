package com.redstevo.ecomerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);


        /*Handle moving to the registration page.*/
        TextView registrationPageLink = findViewById(R.id.register_link);
        registrationPageLink.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(registerIntent);
        });

        /*Handle the login click*/
        Button loginButton = findViewById(R.id.login_btn);

        loginButton.setOnClickListener(view -> {

            /*username input login page*/
            EditText usernameInput = findViewById(R.id.username_input);
            Toast.makeText(LoginActivity.this, usernameInput.getText(), Toast.LENGTH_LONG).show();

            /*password input registration page.*/
            EditText loginPasswordInput = findViewById(R.id.password_input);
            Toast.makeText(LoginActivity.this,loginPasswordInput.getText(), Toast.LENGTH_LONG).show();
        });

    }
}