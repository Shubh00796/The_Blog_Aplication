package com.Personal.blogapplication.SecurityConfig;

import com.Personal.blogapplication.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing purposes (not recommended for production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").authenticated() // Protect "/admin/**"
                        .requestMatchers("/user/**").permitAll() // Allow all requests to "/user/**"
                        .anyRequest().permitAll() // Allow all other requests
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .permitAll() // Allow access to the login page
                )
                .logout(logout -> logout
                        .permitAll() // Allow everyone to log out
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> userService.findByUsername(username)
                .map(userDTO -> org.springframework.security.core.userdetails.User
                        .withUsername(userDTO.getUsername())
                        .password(userDTO.getPassword()) // Ensure `password` is available in `UserDTO`
                        .roles(userDTO.getRole()) // Add roles
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
