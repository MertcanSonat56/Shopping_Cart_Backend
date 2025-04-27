package com.wawex.dream_shops.response;

import com.wawex.dream_shops.dto.UserDto;
import com.wawex.dream_shops.model.User;

public class UserApiResponse {

    private String message;
    private UserDto user;

    public UserApiResponse(String message, UserDto user) {
        this.message = message;
        this.user = user;
    }
}
