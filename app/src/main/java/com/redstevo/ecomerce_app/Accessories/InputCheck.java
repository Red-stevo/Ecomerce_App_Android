package com.redstevo.ecomerce_app.Accessories;

import android.graphics.Bitmap;

import com.redstevo.ecomerce_app.CustomerException.weakPasswordException;
import com.redstevo.ecomerce_app.Models.ImagePreviewModel;

import java.util.List;

public interface InputCheck {

    void passwordStrength(String password) throws weakPasswordException;

    List<String> uploadImages(List<Bitmap> imagePreviewModelList, OnImageUploadComplete callback);
}
