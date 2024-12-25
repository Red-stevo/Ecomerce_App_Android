package com.redstevo.ecomerce_app;

import com.redstevo.ecomerce_app.Accessories.InputCheck;

import java.util.regex.Pattern;

public class AccessoriesImpl implements InputCheck {
    @Override
    public Boolean passwordStrength(String password) {



        Pattern pattern = Pattern.compile("^[a-z][A-Z][0-9][@!#$%^&*()_+=/\\-?>\\]\\[<.,~`]$");
        return null;
    }
}
