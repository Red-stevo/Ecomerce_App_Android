package com.redstevo.ecomerce_app.Models;

import android.net.Uri;

public class ImagePreviewModel {
    private String imageVideoUrl;

    private Uri imageVideoUri;

    public String getImageVideoUrl() {
        return imageVideoUrl;
    }

    public void setImageVideoUrl(String imageVideoUrl) {
        this.imageVideoUrl = imageVideoUrl;
    }

    public Uri getImageVideoUri() {
        return imageVideoUri;
    }

    public void setImageVideoUri(Uri imageVideoUri) {
        this.imageVideoUri = imageVideoUri;
    }
}
