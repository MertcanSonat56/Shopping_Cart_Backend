package com.wawex.dream_shops.service.user;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.wawex.dream_shops.dto.UserDto;
import com.wawex.dream_shops.exceptions.AlreadyExistsException;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.User;
import com.wawex.dream_shops.repository.UserRepository;
import com.wawex.dream_shops.request.CreateUserRequest;
import com.wawex.dream_shops.request.UserUpdateRequest;


@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        
        return Optional.of(request).filter(user -> !userRepository.existsByEmail(request.getEmail()))
        .map(req -> {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());

            return userRepository.save(user);

        }).orElseThrow(() -> new AlreadyExistsException("Oops" + request.getEmail() + " already exists!")); 
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {

        return  userRepository.findById(userId).map(existingUser ->{
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());

            return userRepository.save(existingUser);
        
        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

    }

    @Override
    public void deleteUser(Long userId) {
        
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("User not found");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
