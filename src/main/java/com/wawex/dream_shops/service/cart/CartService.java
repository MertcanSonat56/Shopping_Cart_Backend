package com.wawex.dream_shops.service.cart;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.Cart;
import com.wawex.dream_shops.model.User;
import com.wawex.dream_shops.repository.CartItemRepository;
import com.wawex.dream_shops.repository.CartRepository;


@Service
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        
    }

    @Override
    public Cart getCart(Long id) {

        Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);

        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        
        cart.getItems().clear();
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        
        Cart cart = getCart(id);
        return cart.getTotalAmount();

    }

    @Override
    public Cart initializeNewCart(User user) {
        
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    //cart.setId(cartIdGenerator.incrementAndGet());
                    cart.setUser(user);
                    //cart.setTotalAmount(BigDecimal.ZERO);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}



