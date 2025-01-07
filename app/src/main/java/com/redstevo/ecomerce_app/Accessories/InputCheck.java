package com.redstevo.ecomerce_app.Accessories;

import android.graphics.Bitmap;

import com.redstevo.ecomerce_app.CustomerException.weakPasswordException;

import java.util.List;

public interface InputCheck {

    void passwordStrength(String password) throws weakPasswordException;

    void uploadImages(List<Bitmap> imagePreviewModelList, OnImageUploadComplete callback);
}
