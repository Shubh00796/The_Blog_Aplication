package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.AuthRequestDTO;
import com.Personal.blogapplication.Dtos.AuthResponseDTO;
import com.Personal.blogapplication.Entity.UserForWorkout;
import com.Personal.blogapplication.Mapper.UserForWorkoutMapper;
import com.Personal.blogapplication.Repo.UserForWorkoutReposiotry;
import com.Personal.blogapplication.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserForWorkoutReposiotry userForWorkoutReposiotry;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserForWorkoutMapper userForWorkoutMapper;

    public AuthResponseDTO register(AuthRequestDTO authRequestDTO) {
        if (authRequestDTO.getPassword() == null || authRequestDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        UserForWorkout user = UserForWorkout.builder()
                .username(authRequestDTO.getUsername())
                .password(passwordEncoder.encode(authRequestDTO.getPassword()))
                .build();

        UserForWorkout savedUser = userForWorkoutReposiotry.save(user);

        String token = jwtUtil.generateToken(savedUser.getUsername());

        return userForWorkoutMapper.toAuthResponseDTO(savedUser, token);
    }


    public AuthResponseDTO login(AuthRequestDTO requestDTO){
        UserForWorkout user = userForWorkoutReposiotry.findByUsername(requestDTO.getUsername())
                .orElse(null);  // Avoid throwing exception if user is not found

        if (user == null) {
            throw new RuntimeException("User with given username not found.");
        }

        if(!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return userForWorkoutMapper.toAuthResponseDTO(user, token);
    }


}
