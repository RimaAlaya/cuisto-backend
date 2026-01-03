package com.cuisto.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document(collection = "recipes")
public class Recipe {
    @Id
    private String id;

    private String title;
    private String description;
    private String imageUrl;

    // Categorization
    private String cuisine; // "Italian", "Tunisian", "French", etc.
    private String mealType; // "breakfast", "lunch", "dinner", "dessert", "snack"
    private Set<String> dietaryTags = new HashSet<>(); // "vegan", "gluten-free", "low-carb"
    private String difficulty; // "easy", "medium", "hard"

    // Cooking details
    private Integer prepTimeMinutes;
    private Integer cookTimeMinutes;
    private Integer servings;

    // Ingredients
    private List<Ingredient> ingredients = new ArrayList<>();

    // Instructions
    private List<String> instructions = new ArrayList<>();

    // Nutrition (per serving)
    private Nutrition nutrition;

    // Social
    private String authorId; // User who created/uploaded
    private Integer likes = 0;
    private Double averageRating = 0.0;
    private Integer totalRatings = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Data
    public static class Ingredient {
        private String name;
        private String amount; // "2 cups", "500g", etc.
        private String unit;
        private Boolean optional = false;
    }

    @Data
    public static class Nutrition {
        private Integer calories;
        private Double protein; // grams
        private Double carbs; // grams
        private Double fat; // grams
        private Double fiber; // grams
    }
}