package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.UserForExpenceDTO;
import com.Personal.blogapplication.Entity.UserForExpence;
import com.Personal.blogapplication.Exceptions.UserRegistrationException;
import com.Personal.blogapplication.Interface.UserExpenceService;
import com.Personal.blogapplication.Repo.UserForExpenceRepository;
import com.Personal.blogapplication.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserExpenceServiceImpl implements UserExpenceService {

    private final UserForExpenceRepository userForExpenceRepository;
    private final PasswordEncoder passwordEncoder; // Injected from SecurityBeansConfig
    private final JwtUtil jwtUtil;

    public UserForExpence registerUser(UserForExpenceDTO userForExpenceDTO) throws UserRegistrationException {
        if (userForExpenceDTO.getUsername() == null || userForExpenceDTO.getUsername().isEmpty() ||
                userForExpenceDTO.getEmail() == null || userForExpenceDTO.getEmail().isEmpty()) {
            throw new UserRegistrationException("Username and Email cannot be empty");
        }

        if (userForExpenceRepository.findByUsername(userForExpenceDTO.getUsername()).isPresent() ||
                userForExpenceRepository.findByEmail(userForExpenceDTO.getEmail()).isPresent()) {
            throw new UserRegistrationException("Username or email already exists");
        }

        try {
            UserForExpence userForExpence = new UserForExpence();
            userForExpence.setUsername(userForExpenceDTO.getUsername());
            userForExpence.setPassword(passwordEncoder.encode(userForExpenceDTO.getPassword()));
            userForExpence.setEmail(userForExpenceDTO.getEmail());

            return userForExpenceRepository.save(userForExpence);
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage(), e);
            throw new UserRegistrationException("Failed to register user: " + e.getMessage(), e);
        }
    }

    public String authenticateUser(String username, String password) {
        UserForExpence user = userForExpenceRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials for user: " + username);
        }

        return jwtUtil.generateToken(username);
    }
}