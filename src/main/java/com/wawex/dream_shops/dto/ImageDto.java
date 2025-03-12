package com.wawex.dream_shops.dto;

import lombok.Data;

@Data
public class ImageDto {

    private Long imageId;
    private String imageName;
    private String downloadUrl;

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
