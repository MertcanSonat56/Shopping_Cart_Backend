package com.wawex.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wawex.dream_shops.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
