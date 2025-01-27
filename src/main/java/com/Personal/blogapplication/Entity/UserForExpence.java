package com.Personal.blogapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_for_expence") // Avoid using reserved keywords like "user" for table names
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForExpence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private Long id;

    @Column(nullable = false, unique = true) // Ensure username is unique and not null
    private String username;

    @Column(nullable = false) // Password must not be null
    private String password;

    @Column(nullable = false, unique = true) // Email must be unique and not null
    private String email;
}