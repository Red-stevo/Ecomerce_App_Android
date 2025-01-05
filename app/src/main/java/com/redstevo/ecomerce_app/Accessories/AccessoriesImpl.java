package com.redstevo.ecomerce_app.Accessories;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.redstevo.ecomerce_app.CustomerException.weakPasswordException;
import com.redstevo.ecomerce_app.Models.ImagePreviewModel;

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

    @Override
    public List<String> uploadImages(
            List<Bitmap> imagePreviewModelList, OnImageUploadComplete callback) {

        List<String> imageUrls = new ArrayList<>();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        imagePreviewModelList.forEach(image -> {
            StorageReference imageReference =
                    storageReference.child("product"+System.currentTimeMillis()+".jpg");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();

            UploadTask uploadTask = imageReference.putBytes(bytes);

            uploadTask.addOnSuccessListener(taskSnapshot -> {
                imageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                    imageUrls.add(uri.toString());

                    Log.d("IMAGE", "uploadImages: "+uri);

                    if (imageUrls.size() == imagePreviewModelList.size())
                        callback.onComplete(imageUrls);
                }).addOnFailureListener(callback::onError);
            });
        });
        return Collections.emptyList();
    }
}
