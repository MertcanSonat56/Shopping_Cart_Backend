package com.wawex.dream_shops.response;

import com.wawex.dream_shops.model.Product;

public class ProductApiResponse {

    private String message;
    private Product product;

    public ProductApiResponse(String message, Product product) {
        this.message = message;
        this.product = product;
    }
}
