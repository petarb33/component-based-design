package com.f1.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Size(max = 100)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Size(max = 150)
    @NotNull
    @Column(name = "email", nullable = false, length = 150)
    @Email(message = "Must be a valid email address")
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

}