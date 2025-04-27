package com.wawex.dream_shops.service.cart;

import com.wawex.dream_shops.model.CartItem;

public interface ICartItemService {

    void addItemToCart(Long cart, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
    
    CartItem getCartItem(Long cartId, Long productId);
}
