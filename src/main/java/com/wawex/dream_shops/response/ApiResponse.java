package com.wawex.dream_shops.response;

import java.util.List;
import com.wawex.dream_shops.dto.ImageDto;


public class ApiResponse {
    private String message;
    private List<ImageDto> imageDtos;

    public ApiResponse() {
        // default constructor
    }

    public ApiResponse(String message, List<ImageDto> imageDtos) {
        this.message = message;
        this.imageDtos = imageDtos;
    }
    

}


