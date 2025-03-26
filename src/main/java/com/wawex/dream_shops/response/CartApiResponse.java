package com.wawex.dream_shops.response;


import com.wawex.dream_shops.model.Cart;

public class CartApiResponse {

    private String message;
    //private List<Cart> cart;
    private Object cart;

    public CartApiResponse(String message, Object cart) {
        this.message = message;
        this.cart = cart;
    }
}
