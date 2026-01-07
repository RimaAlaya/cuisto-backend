package com.cuisto.backend.service;

import com.cuisto.backend.model.Recipe;
import com.cuisto.backend.model.User;
import com.cuisto.backend.repository.RecipeRepository;
import com.cuisto.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AIRecommendationService {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public Map<String, Object> getPersonalizedRecommendations(String userEmail) {
        // Find user by EMAIL (not ID)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Recipe> recommendedRecipes;

        // Get recipes based on user's favorite cuisines
        if (!user.getFavoriteCuisines().isEmpty()) {
            recommendedRecipes = user.getFavoriteCuisines().stream()
                    .flatMap(cuisine -> recipeRepository.findByCuisine(cuisine).stream())
                    .limit(10)
                    .collect(Collectors.toList());

            // Filter by dietary restrictions
            if (!user.getDietaryRestrictions().isEmpty()) {
                recommendedRecipes = recommendedRecipes.stream()
                        .filter(recipe -> recipe.getDietaryTags().stream()
                                .anyMatch(user.getDietaryRestrictions()::contains))
                        .collect(Collectors.toList());
            }
        } else {
            // If no preferences, just return some recipes
            recommendedRecipes = recipeRepository.findAll().stream()
                    .limit(10)
                    .collect(Collectors.toList());
        }

        return Map.of(
                "userId", user.getId(),
                "userName", user.getFirstName() + " " + user.getLastName(),
                "recommendations", recommendedRecipes,
                "message", recommendedRecipes.isEmpty() ?
                        "No recipes available yet. Try adding some recipes!" :
                        "Personalized recipes based on your preferences"
        );
    }

    public Recipe getDailyChallenge(String userEmail) {
        // Find user by EMAIL (not ID)
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get all recipes
        List<Recipe> allRecipes = recipeRepository.findAll();

        if (allRecipes.isEmpty()) {
            throw new RuntimeException("No recipes available for daily challenge");
        }

        // Pick a random recipe for the daily challenge
        Recipe challengeRecipe = allRecipes.get(
                (int) (Math.random() * allRecipes.size())
        );

        return challengeRecipe;
    }

    public List<Recipe> getRecipesByPantryItems(String userEmail, List<String> pantryIngredients) {
        // Find user by EMAIL
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get all recipes and filter by available ingredients
        return recipeRepository.findAll().stream()
                .filter(recipe -> {
                    List<String> recipeIngredients = recipe.getIngredients().stream()
                            .map(ing -> ing.getName().toLowerCase())
                            .collect(Collectors.toList());

                    // Check if pantry has at least 50% of the ingredients
                    long matchCount = recipeIngredients.stream()
                            .filter(ing -> pantryIngredients.stream()
                                    .anyMatch(pantryIng -> pantryIng.toLowerCase().contains(ing)))
                            .count();

                    return matchCount >= (recipeIngredients.size() * 0.5);
                })
                .limit(10)
                .collect(Collectors.toList());
    }
}