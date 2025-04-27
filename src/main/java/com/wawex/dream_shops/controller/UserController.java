package com.wawex.dream_shops.controller;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wawex.dream_shops.dto.UserDto;
import com.wawex.dream_shops.exceptions.AlreadyExistsException;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.User;
import com.wawex.dream_shops.request.CreateUserRequest;
import com.wawex.dream_shops.request.UserUpdateRequest;
import com.wawex.dream_shops.response.UserApiResponse;
import com.wawex.dream_shops.service.user.IUserService;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<UserApiResponse> getUserById(@PathVariable Long id) {
        try {

            User user = userService.getUserById(id);
            UserDto userDto = userService.convertUserToDto(user);

            return ResponseEntity.ok(new UserApiResponse("Success" ,userDto));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new UserApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<UserApiResponse> createUser(@RequestBody CreateUserRequest request) {
        try {

            User user = userService.createUser(request);
            UserDto userDto = userService.convertUserToDto(user);

            return ResponseEntity.ok(new UserApiResponse("Create User Success!", userDto));
        }

        catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new UserApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<UserApiResponse> updateUser(@RequestBody UserUpdateRequest request,@PathVariable Long userId) {
        try {

            User user = userService.updateUser(request, userId);
            UserDto userDto = userService.convertUserToDto(user);

            return ResponseEntity.ok(new UserApiResponse("Update User Success!", userDto));
        }
        
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new UserApiResponse(e.getMessage(), null));
        }

    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<UserApiResponse> deleteUser(@PathVariable Long userId) {
        try {

            userService.deleteUser(userId);
            return ResponseEntity.ok(new UserApiResponse("Delete User Success!", null));
        } 
        
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new UserApiResponse(e.getMessage(), null));
        }
    }


}
