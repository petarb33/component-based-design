package com.f1.user_service.controller;

import com.f1.user_service.model.User;
import com.f1.user_service.repository.UserRepository;
import com.f1.user_service.request.SignUpRequest;
import com.f1.user_service.request.UserLoginRequest;
import com.f1.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody @Valid SignUpRequest request) {
        userService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User has been signed up");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid email");
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        return ResponseEntity.ok("validKey123");
    }

}
