package com.wawex.dream_shops.response;

import com.wawex.dream_shops.model.User;

public class UserApiResponse {

    private String message;
    private User user;

    public UserApiResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }
}
