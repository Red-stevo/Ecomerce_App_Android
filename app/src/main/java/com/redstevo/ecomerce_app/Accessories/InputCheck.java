package com.redstevo.ecomerce_app.Accessories;
import CustomerException.weakPasswordException;

public interface InputCheck {

    void passwordStrength(String password) throws weakPasswordException;
}
