package com.example.readRent.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.readRent.Dto.SignInRequest;
import com.example.readRent.Dto.SignUpRequest;
import com.example.readRent.Services.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    /**
     * Registration endpoint.
     *
     * @param request RegisterRequest containing user details
     * @return Response message
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpRequest request) {
        // Registration logic
        System.out.println(request);
        ResponseEntity<?> message = authService.register(request);
        return ResponseEntity.ok(message);
    }

    /**
     * Login endpoint.
     *
     * @param request LoginRequest containing email and password
     * @return JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignInRequest request) {
        ResponseEntity<?> token = authService.login(request);
        return ResponseEntity.ok("Bearer " + token);
    }

}
