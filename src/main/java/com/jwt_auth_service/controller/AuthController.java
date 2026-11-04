package com.jwt_auth_service.controller;

import com.jwt_auth_service.dto.request.LoginRequestDto;
import com.jwt_auth_service.dto.request.RegisterRequestDto;
import com.jwt_auth_service.dto.response.AuthResponse;
import com.jwt_auth_service.service.AuthService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody RegisterRequestDto request
            ){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequestDto request
            ){
        return authService.login(request);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

}
