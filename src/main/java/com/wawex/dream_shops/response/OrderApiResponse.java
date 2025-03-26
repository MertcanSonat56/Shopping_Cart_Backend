package com.wawex.dream_shops.response;

import com.wawex.dream_shops.model.Order;

public class OrderApiResponse {

    private String message;
    private Order order;

    public OrderApiResponse(String message, Order order) {
        this.message = message;
        this.order = order;
    }
}
