package com.redstevo.ecomerce_app.Activities.Registration;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.redstevo.ecomerce_app.Accessories.AccessoriesImpl;
import com.redstevo.ecomerce_app.Accessories.InputCheck;
import com.redstevo.ecomerce_app.Activities.Login.LoginActivity;
import com.redstevo.ecomerce_app.R;

public class RegistrationActivity extends AppCompatActivity {

    InputCheck inputCheck = new AccessoriesImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /*handle the login page link*/
        TextView textView = findViewById(R.id.login_page_link);
        textView.setOnClickListener(view -> {
            Intent loginPageIntent =
                    new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(loginPageIntent);
        });


        /*Handle the registration button click.*/
        Button regButton = findViewById(R.id.register_btn);
        regButton.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {

        /*check if any filed is empty*/

        //Username
        EditText usernameRegInput = findViewById(R.id.reg_username);
        String username = String.valueOf(usernameRegInput.getText());

        //Password
        EditText passwordRegInput = findViewById(R.id.reg_password);
        String password = String.valueOf(passwordRegInput.getText());

        //Confirm Password.
        EditText confirmPasswordRegInput = findViewById(R.id.reg_confirm_password);
        String confirmPassword = String.valueOf(confirmPasswordRegInput.getText());


        /*check if empty*/
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "All Fields Are Required.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        /*Check if the two password match.*/
        if (!password.matches(confirmPassword)) {
            Toast.makeText(RegistrationActivity.this, "Passwords Did Not Match",
                    Toast.LENGTH_LONG).show();

            // Load the custom shape drawable from the XML file
            Drawable redBorderBackground = getResources().getDrawable(R.drawable.red_border);

            /*Set the red border to the field.*/
            passwordRegInput.setBackground(redBorderBackground);
            confirmPasswordRegInput.setBackground(redBorderBackground);

            return;
        }

        try {
            inputCheck.passwordStrength(password);
        }catch (Exception exception){
            Toast.makeText(RegistrationActivity.this,
                    exception.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}