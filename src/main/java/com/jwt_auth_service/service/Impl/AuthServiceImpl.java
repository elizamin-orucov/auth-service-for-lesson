package com.jwt_auth_service.service.Impl;

import com.jwt_auth_service.dto.request.LoginRequestDto;
import com.jwt_auth_service.dto.request.RegisterRequestDto;
import com.jwt_auth_service.dto.response.AuthResponse;
import com.jwt_auth_service.entities.UserEntity;
import com.jwt_auth_service.repository.UserRepository;
import com.jwt_auth_service.security.JwtService;
import com.jwt_auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    @Override
    public AuthResponse register(RegisterRequestDto request) {

        if ( repository.existsByEmail(request.getEmail()) ){
            throw new RuntimeException("bu email artiq var");
        }
         // mapper a cevirlmelidir
        UserEntity user = new UserEntity();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        repository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequestDto request) {
        UserEntity user = repository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("email ve ya sifre yanlisdir")
                        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("email ve ya sifre yanlisdir");
        }

        String token = jwtService.generateToken(request.getEmail());


        return new AuthResponse(token);
    }
}
