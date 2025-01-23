package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.UserDTO;
import com.Personal.blogapplication.Entity.User;
import com.Personal.blogapplication.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDTO> findByUsername(String username) {
        try {
            return userRepository.findByUsername(username)
                    .map(user -> new UserDTO(user.getUsername(), user.getRole()));
        } catch (Exception e) {
            // Log error and rethrow or handle accordingly
            throw new RuntimeException("Error fetching user by username: " + username, e);
        }
    }
    public UserDTO saveUser(UserDTO userDTO) {
        try {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
            user.setRole(userDTO.getRole());
            User savedUser = userRepository.save(user);
            return new UserDTO(savedUser.getUsername(), null, savedUser.getRole()); // Return without password
        } catch (Exception e) {
            throw new RuntimeException("Error saving user", e);
        }
    }


}
