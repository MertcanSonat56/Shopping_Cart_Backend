package com.wawex.dream_shops.service.cart;

import java.math.BigDecimal;
import com.wawex.dream_shops.model.Cart;

public interface ICartService {
    
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Long initializeNewCart();
    Cart getCartByUserId(Long userId);
}
