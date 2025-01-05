package com.redstevo.ecomerce_app.Accessories;

import com.redstevo.ecomerce_app.CustomerException.ImagesUploadFailed;

import java.util.List;

public interface OnImageUploadComplete {
    void onComplete(List<String> imageUrls);

    void onError(Exception exception);
}
