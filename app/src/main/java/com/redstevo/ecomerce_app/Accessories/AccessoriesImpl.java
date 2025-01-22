package com.redstevo.ecomerce_app.Accessories;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.cloudinary.Configuration;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.gson.JsonObject;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.model.TaskInfo;
import com.redstevo.ecomerce_app.CustomerException.weakPasswordException;
import com.redstevo.ecomerce_app.Models.NewProductModel;

import lombok.val;

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

    public void initCloudinaryManager(Context context) {
        Configuration configuration = new Configuration();
        configuration.apiKey = "813761264889447";
        configuration.apiSecret = "s9HCzuAuiAJbTyestMw9P9zJwAI";
        configuration.cloudName = "de91mnunt";

        MediaManager.init(context, configuration);
    }


    @Override
    public void uploadImages(Context context,
            List<Bitmap> imagePreviewModelList, OnImageUploadComplete callback) {

        List<String> imageUrls = new ArrayList<>();

        imagePreviewModelList.forEach(image -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, baos);


            MediaManager.get().upload(baos.toByteArray()).callback(new UploadCallback() {
                @Override
                public void onStart(String requestId) {

                }

                @Override
                public void onProgress(String requestId, long bytes, long totalBytes) {

                }

                @Override
                public void onSuccess(String requestId, Map resultData) {

                    imageUrls.add(String.valueOf(resultData.get("secure_url")));

                    if (imageUrls.size() == imagePreviewModelList.size())
                        callback.onComplete(imageUrls);
                }

                @Override
                public void onError(String requestId, ErrorInfo error) {
                    Toast.makeText(context, "Failed to Upload Image : "+error.getDescription(),
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onReschedule(String requestId, ErrorInfo error) {

                }
            }).dispatch();

        });
    }
}
