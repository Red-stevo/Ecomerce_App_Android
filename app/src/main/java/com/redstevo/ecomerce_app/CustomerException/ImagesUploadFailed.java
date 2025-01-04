package com.redstevo.ecomerce_app.CustomerException;

public class ImagesUploadFailed extends RuntimeException {
    public ImagesUploadFailed(String message) {

        super(message);
    }
}
