package com.wawex.dream_shops.service.user;

import com.wawex.dream_shops.model.User;
import com.wawex.dream_shops.request.CreateUserRequest;
import com.wawex.dream_shops.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);
    
}




