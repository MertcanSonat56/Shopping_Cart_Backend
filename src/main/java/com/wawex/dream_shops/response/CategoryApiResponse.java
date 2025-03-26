package com.wawex.dream_shops.response;

import com.wawex.dream_shops.model.Category;

public class CategoryApiResponse {

    private String message;
    private Category category;

    public CategoryApiResponse(String message, Category category) {
        this.message = message;
        this.category = category;
    }
}
