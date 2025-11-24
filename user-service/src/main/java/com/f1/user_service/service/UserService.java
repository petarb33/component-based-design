package com.f1.user_service.service;

import com.f1.user_service.exception.EntityAlreadyExistsException;
import com.f1.user_service.model.User;
import com.f1.user_service.repository.UserRepository;
import com.f1.user_service.request.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(SignUpRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EntityAlreadyExistsException("User with this email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
