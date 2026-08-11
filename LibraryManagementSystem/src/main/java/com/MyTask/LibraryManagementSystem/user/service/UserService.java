package com.MyTask.LibraryManagementSystem.user.service;

import com.MyTask.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.MyTask.LibraryManagementSystem.user.dto.UpdateUserRequest;
import com.MyTask.LibraryManagementSystem.user.dto.UserResponse;
import com.MyTask.LibraryManagementSystem.user.entity.User;
import com.MyTask.LibraryManagementSystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public UserResponse updateUser(UpdateUserRequest userRequest) {
        User user = getCurrentUser();

        if (userRequest.getFullName() != null) {
            user.setFullName(userRequest.getFullName());
        }

        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        }

        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }

        if (userRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserResponse.class);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }
}

