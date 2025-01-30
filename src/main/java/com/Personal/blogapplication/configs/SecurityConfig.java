package com.Personal.blogapplication.configs;

import com.Personal.blogapplication.Utils.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/workouts/**").authenticated()  // Protect /api/workouts/** with authentication
                        .requestMatchers("/admin/**").authenticated()  // Admin routes, only authenticated users can access
                                .requestMatchers("/v3/api-docs/**").permitAll() // Allow access to OpenAPI spec
                                .requestMatchers("/swagger-ui.html").permitAll() // Allow access to Swagger UI
                                .requestMatchers("/swagger-ui/**").permitAll()  // Allow access to Swagger UI resources
                        .requestMatchers("/user/**").permitAll()  // Public routes, no authentication required
                        .anyRequest().permitAll()  // Allow all other requests (e.g., for public resources)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // Add JwtFilter before standard security filters
                .logout(logout -> logout.permitAll());  // Allow public access to logout

        return http.build();
    }
}
