package com.jwt_auth_service.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;

    private String password;
}
