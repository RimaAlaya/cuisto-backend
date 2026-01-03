package com.cuisto.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;

    private Set<String> roles = new HashSet<>(); // "USER", "ADMIN"

    // Profile
    private String avatarUrl;
    private String bio;

    // Preferences
    private Set<String> favoriteCuisines = new HashSet<>(); // "Italian", "Tunisian", etc.
    private Set<String> dietaryRestrictions = new HashSet<>(); // "vegan", "gluten-free", etc.

    // Gamification
    private Integer cookingStreak = 0;
    private Integer totalRecipesCooked = 0;
    private Set<String> badgesEarned = new HashSet<>();
    private Integer points = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastLoginAt;
}