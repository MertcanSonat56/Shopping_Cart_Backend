package com.wawex.dream_shops.service.order;

import java.util.List;
import com.wawex.dream_shops.model.Order;

public interface IOrderService {

    Order placeOrder(Long userId);
    Order getOrder(Long orderId);
    List<Order> getUserOrders(Long userId);
}
