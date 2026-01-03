package com.cuisto.backend.service;

import com.cuisto.backend.dto.AuthRequest;
import com.cuisto.backend.dto.AuthResponse;
import com.cuisto.backend.dto.RegisterRequest;
import com.cuisto.backend.model.User;
import com.cuisto.backend.repository.UserRepository;
import com.cuisto.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Check if user exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Create new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setFavoriteCuisines(request.getFavoriteCuisines() != null ?
                request.getFavoriteCuisines() : new HashSet<>());
        user.setDietaryRestrictions(request.getDietaryRestrictions() != null ?
                request.getDietaryRestrictions() : new HashSet<>());
        user.getRoles().add("USER");

        userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public AuthResponse login(AuthRequest request) {
        // Authenticate
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Find user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update last login
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getFirstName(), user.getLastName());
    }
}