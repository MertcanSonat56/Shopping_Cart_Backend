package com.wawex.dream_shops.controller;

import java.math.BigDecimal;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.Cart;
import com.wawex.dream_shops.response.CartApiResponse;
import com.wawex.dream_shops.service.cart.ICartService;


@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {

    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<CartApiResponse> getCart(@PathVariable Long cartId) {
        
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new CartApiResponse("Success",  cart));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CartApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<CartApiResponse> clearCart(@PathVariable Long cartId) {
        
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new CartApiResponse("Clear cart success", null));
        }
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CartApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<CartApiResponse> getTotalAmount(@PathVariable Long cartId) {
        
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return  ResponseEntity.ok(new CartApiResponse("Total Price", totalPrice));
        }
        
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CartApiResponse(e.getMessage(), null));
        }
    }

    
}
