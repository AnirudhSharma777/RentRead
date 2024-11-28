package com.example.readRent.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.readRent.Dto.SignInRequest;
import com.example.readRent.Dto.SignUpRequest;
import com.example.readRent.Entities.Role;
import com.example.readRent.Entities.User;
import com.example.readRent.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Register a new user.
     *
     * @param request RegisterRequest containing user details
     * @return A success message
     */
    public ResponseEntity<?> register(SignUpRequest request) {
        // Check if the user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email already exists");
        }

        // Create a new User entity
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);


        // Save the user to the database
        userRepository.save(user);

        // Return success response
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    /**
     * Authenticate a user and generate a JWT token.
     *
     * @param request LoginRequest containing email and password
     * @return A JWT token
     */
    public ResponseEntity<?> login(SignInRequest request) {
        // Find the user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate the password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Generate a JWT token
        String token = jwtService.generateToken(user);

        // Return the token
        return ResponseEntity.ok(token);
    }
}
