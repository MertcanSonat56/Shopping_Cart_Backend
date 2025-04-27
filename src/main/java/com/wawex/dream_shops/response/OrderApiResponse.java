package com.wawex.dream_shops.response;

import com.wawex.dream_shops.dto.OrderDto;
import com.wawex.dream_shops.model.Order;

public class OrderApiResponse {

    private String message;
    private OrderDto order;

    public OrderApiResponse(String message, OrderDto order) {
        this.message = message;
        this.order = order;
    }
}
