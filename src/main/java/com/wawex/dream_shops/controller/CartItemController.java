package com.wawex.dream_shops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.response.CartApiResponse;
import com.wawex.dream_shops.service.cart.ICartItemService;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import com.wawex.dream_shops.model.User;
import com.wawex.dream_shops.model.Cart;
import com.wawex.dream_shops.service.cart.ICartService;
import com.wawex.dream_shops.service.user.IUserService;


@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    public CartItemController(ICartItemService cartItemService, ICartService cartService, IUserService userService) {
        this.cartItemService = cartItemService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/item/add")
    public ResponseEntity<CartApiResponse> addItemToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        
        try {

            User user = userService.getUserById(1L);
            Cart cart = cartService.initializeNewCart(user);

            cartItemService.addItemToCart(cart.getId(),productId, quantity);
            return ResponseEntity.ok(new CartApiResponse("Add Item Success", null));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CartApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<CartApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {

        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new CartApiResponse("Remove Item Success", null));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CartApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<CartApiResponse> updateItemQuantity(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam Integer quantity) {

        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new CartApiResponse("Update Item Success", null));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CartApiResponse(e.getMessage(), null));
        }
    }
}


