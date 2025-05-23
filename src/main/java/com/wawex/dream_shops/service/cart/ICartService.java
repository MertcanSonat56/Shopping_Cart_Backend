package com.wawex.dream_shops.service.cart;

import java.math.BigDecimal;
import com.wawex.dream_shops.model.Cart;
import com.wawex.dream_shops.model.User;

public interface ICartService {
    
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Cart initializeNewCart(User user);
    Cart getCartByUserId(Long userId);
}
