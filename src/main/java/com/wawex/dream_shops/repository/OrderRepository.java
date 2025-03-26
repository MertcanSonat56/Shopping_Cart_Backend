package com.wawex.dream_shops.repository;

import java.util.List;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wawex.dream_shops.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<Order> findByUserId(Long userId);
}
