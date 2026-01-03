package com.cuisto.backend.dto;

import com.cuisto.backend.model.Recipe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RecipeRequest {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String imageUrl;

    @NotBlank(message = "Cuisine is required")
    private String cuisine;

    @NotBlank(message = "Meal type is required")
    private String mealType;

    private Set<String> dietaryTags;
    private String difficulty;

    @NotNull(message = "Prep time is required")
    private Integer prepTimeMinutes;

    @NotNull(message = "Cook time is required")
    private Integer cookTimeMinutes;

    @NotNull(message = "Servings is required")
    private Integer servings;

    @NotNull(message = "Ingredients are required")
    private List<Recipe.Ingredient> ingredients;

    @NotNull(message = "Instructions are required")
    private List<String> instructions;

    private Recipe.Nutrition nutrition;
}