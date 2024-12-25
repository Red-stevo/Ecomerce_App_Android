package com.redstevo.ecomerce_app.Accessories;
import CustomerException.weakPasswordException;

public interface InputCheck {

    Boolean passwordStrength(String password) throws weakPasswordException;
}
