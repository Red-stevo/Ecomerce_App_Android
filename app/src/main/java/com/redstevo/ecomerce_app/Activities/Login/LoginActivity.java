package com.redstevo.ecomerce_app.Activities.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.redstevo.ecomerce_app.R;
import com.redstevo.ecomerce_app.Activities.Registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);


        /*Handle moving to the registration page.*/
        TextView registrationPageLink = findViewById(R.id.register_link);
        registrationPageLink.setOnClickListener(view -> {
            Intent registerIntent = new Intent(
                    LoginActivity.this, RegistrationActivity.class);
            startActivity(registerIntent);
        });

        /*Handle the login click*/
        Button loginButton = findViewById(R.id.login_btn);

        loginButton.setOnClickListener(view -> {

            /*username input login page*/
            EditText usernameInput = findViewById(R.id.username_input);
             String userName = String.valueOf(usernameInput.getText());

            /*password input registration page.*/
            EditText loginPasswordInput = findViewById(R.id.password_input);
            String password = String.valueOf(loginPasswordInput.getText());

            /*Check if the fields are empty*/
            if(userName.isEmpty() || password.isEmpty())
                Toast.makeText(LoginActivity.this,
                        "All fields Must Be Filled!", Toast.LENGTH_LONG).show();
        });

    }
}