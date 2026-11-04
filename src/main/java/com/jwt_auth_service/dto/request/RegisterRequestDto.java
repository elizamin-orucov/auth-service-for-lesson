package com.jwt_auth_service.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;

    private String email;

    private String password;
}
