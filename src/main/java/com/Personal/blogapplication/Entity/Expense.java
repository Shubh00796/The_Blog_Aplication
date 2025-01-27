package com.Personal.blogapplication.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private Long id;

    @Column(nullable = false) // Email is required to associate the expense with a user
    private String email;

    @Column(nullable = false) // Description of the expense is required
    private String description;

    @Column(nullable = false) // Amount is required
    private Double amount;

    @Column(nullable = false) // Category is required
    private String category; // e.g., Groceries, Leisure, etc.

    @Column(nullable = false) // Date is required
    private String date; // ISO-8601 format (yyyy-MM-dd)
}
