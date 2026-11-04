package com.jwt_auth_service.service;

import com.jwt_auth_service.dto.request.LoginRequestDto;
import com.jwt_auth_service.dto.request.RegisterRequestDto;
import com.jwt_auth_service.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequestDto request);

    AuthResponse login(LoginRequestDto request);
}
