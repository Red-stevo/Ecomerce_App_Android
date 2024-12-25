package com.redstevo.ecomerce_app.Accessories;

import java.util.regex.Pattern;

import CustomerException.weakPasswordException;

public class AccessoriesImpl implements InputCheck {
    @Override
    public Boolean passwordStrength(String password) {

        if (password.length() < 8)
            throw new weakPasswordException("Password Should Have at Least 8 characters.");

        Pattern pattern = Pattern.compile("^[a-z][A-Z][0-9][@!#$%^&*()_+=/\\-?>\\]\\[<.,~`]$");

        if(pattern.matcher(password).matches()) return true;
        else throw new weakPasswordException("Password must contain a capital letter," +
                " a small letter and a special character.");
    }
}
