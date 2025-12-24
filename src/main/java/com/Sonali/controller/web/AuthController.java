package com.Sonali.controller.web;

import com.Sonali.Security.service.JwtService;
import com.Sonali.entity.User;
import com.Sonali.model.domain.AuthResponse;
import com.Sonali.model.dto.LoginRequest;
import com.Sonali.model.dto.RefreshRequest;
import com.Sonali.repo.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String accessToken =
                jwtService.generateAccessToken(user.getEmail(), user.getRole().name());

        String refreshToken =
                jwtService.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {

        String email = jwtService.extractEmail(request.getRefreshToken());

        User user = userRepository.findByEmail(email).orElseThrow();

        String newAccessToken =
                jwtService.generateAccessToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(newAccessToken, request.getRefreshToken());
    }
}

