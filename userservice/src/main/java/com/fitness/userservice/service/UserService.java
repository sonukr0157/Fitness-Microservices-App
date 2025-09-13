package com.fitness.userservice.service;

import com.fitness.userservice.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository repository;

    public UserResponse register(RegisterRequest request) {

        if(repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = repository.save(user);

        UserResponse response = new UserResponse();
        response.setId(String.valueOf(savedUser.getId()));
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        response.setPassword(savedUser.getPassword());

        return response;
    }

    public UserResponse getUserProfile(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setPassword(user.getPassword());

        return response;
    }

    public Boolean existByUserId(String userId) {
        log.info("Validating user with ID: {}", userId);
        return  repository.existsById(userId);
    }
}
