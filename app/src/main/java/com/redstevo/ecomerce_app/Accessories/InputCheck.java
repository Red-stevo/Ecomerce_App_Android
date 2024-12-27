package com.redstevo.ecomerce_app.Accessories;
import com.redstevo.ecomerce_app.CustomerException.weakPasswordException;

public interface InputCheck {

    void passwordStrength(String password) throws weakPasswordException;
}
