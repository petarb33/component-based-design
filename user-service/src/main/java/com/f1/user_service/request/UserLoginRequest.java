package com.f1.user_service.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.Email;

@Data
public class UserLoginRequest {
    @NotBlank(message = "Email can't be empty")
    @Email
    private String email;

    private String password;
}
