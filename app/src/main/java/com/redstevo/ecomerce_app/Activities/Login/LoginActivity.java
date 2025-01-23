package com.redstevo.ecomerce_app.Activities.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.redstevo.ecomerce_app.Activities.GeneralView.GeneralActivity;
import com.redstevo.ecomerce_app.Activities.ProductsView.SearchProduct;
import com.redstevo.ecomerce_app.R;
import com.redstevo.ecomerce_app.Activities.Registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {

    private final FirebaseAuth mAuth;

    public LoginActivity() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            currentUser.reload();
        }
        setContentView(R.layout.login_activity);


        /*Handle moving to the registration page.*/
        TextView registrationPageLink = findViewById(R.id.register_link);
        registrationPageLink.setOnClickListener(view -> {
            startActivity(new Intent(
                    LoginActivity.this, RegistrationActivity.class));
        });

        /*Handle the login click*/
        Button loginButton = findViewById(R.id.login_btn);

        loginButton.setOnClickListener(view -> {

            /*username input login page*/
            EditText usernameInput = findViewById(R.id.username_input);
            String email = String.valueOf(usernameInput.getText());

            /*password input registration page.*/
            EditText loginPasswordInput = findViewById(R.id.password_input);
            String password = String.valueOf(loginPasswordInput.getText());

            /*Check if the fields are empty*/
            if (email.isEmpty() || password.isEmpty())
                Toast.makeText(LoginActivity.this,
                        "All fields Must Be Filled!", Toast.LENGTH_LONG).show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            user.reload();
                            startActivity(new Intent(this, SearchProduct.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });
        });

    }
}