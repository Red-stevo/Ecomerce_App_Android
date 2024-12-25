package com.redstevo.ecomerce_app.Accessories;

import android.widget.Toast;

import com.redstevo.ecomerce_app.Activities.Registration.RegistrationActivity;

import java.util.regex.Pattern;

import CustomerException.weakPasswordException;

public class AccessoriesImpl implements InputCheck {
    @Override
    public void passwordStrength(String password) {

        if (password.length() < 8)
            throw new weakPasswordException("Password Should Have at Least 8 characters.");

        Pattern pattern = Pattern.compile("^?.*[a-zA-Z]?.*[0-9]?.*[@!#$%^&*()_+=/?><.,~ `]");

        if(!pattern.matcher(password).matches())
            throw new weakPasswordException("Password must contain a capital letter," +
                    " a small letter and a special character.(@!#$%^&*()_+=/?><.,~`)");
    }
}
