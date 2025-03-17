package com.wawex.dream_shops.dto;

import lombok.Data;

@Data
public class ImageDto {

    private Long id;
    private String imageName;
    private String downloadUrl;
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for 'imageName'
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    // Getter and Setter for 'downloadUrl'
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    
}
