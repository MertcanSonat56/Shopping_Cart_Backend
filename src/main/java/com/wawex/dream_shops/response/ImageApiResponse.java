package com.wawex.dream_shops.response;

import com.wawex.dream_shops.model.Image;

public class ImageApiResponse {

    private String message;
    private Image image;

    public ImageApiResponse(String message, Image image) {
        this.message = message;
        this.image = image;
    }
}
