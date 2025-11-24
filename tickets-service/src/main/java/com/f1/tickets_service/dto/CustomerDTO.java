package com.f1.tickets_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    private Integer id;

    @Size(max = 100)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(max = 150)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 50)
    private String phone;
}
