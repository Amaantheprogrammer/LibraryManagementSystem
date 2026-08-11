package com.MyTask.LibraryManagementSystem.auth.service;

import com.MyTask.LibraryManagementSystem.auth.dto.AuthResponse;
import com.MyTask.LibraryManagementSystem.auth.dto.LoginRequest;
import com.MyTask.LibraryManagementSystem.auth.dto.RegisterRequest;
import com.MyTask.LibraryManagementSystem.auth.jwt.JwtService;
import com.MyTask.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.MyTask.LibraryManagementSystem.exception.FieldAlreadyExistsException;
import com.MyTask.LibraryManagementSystem.user.entity.User;
import com.MyTask.LibraryManagementSystem.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email " + request.getUsername()));
        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new FieldAlreadyExistsException("User already exists with username: " + request.getUsername());
        }
        User user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
    }

}
