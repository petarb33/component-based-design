package com.f1.user_service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    @JsonProperty("firstname")
    private String firstName;
    @NotBlank
    @JsonProperty("lastname")
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
