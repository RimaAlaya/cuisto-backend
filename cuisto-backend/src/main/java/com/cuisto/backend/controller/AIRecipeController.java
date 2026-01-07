package com.cuisto.backend.controller;

import com.cuisto.backend.dto.AIRecipeRequest;
import com.cuisto.backend.dto.RecipeRequest;
import com.cuisto.backend.model.Recipe;
import com.cuisto.backend.service.AIRecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-recipes")
@RequiredArgsConstructor
public class AIRecipeController {

    private final AIRecipeService aiRecipeService;

    @PostMapping("/generate")
    public ResponseEntity<RecipeRequest> generateRecipe(
            @Valid @RequestBody AIRecipeRequest request,
            Authentication authentication) {
        String userEmail = authentication.getName();
        RecipeRequest generatedRecipe = aiRecipeService.generateRecipe(request, userEmail);
        return ResponseEntity.ok(generatedRecipe);
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<String>> getRecipeSuggestions(
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String mealType,
            @RequestParam(required = false) List<String> ingredients,
            Authentication authentication) {
        String userEmail = authentication.getName();
        List<String> suggestions = aiRecipeService.getRecipeSuggestions(
                cuisine, mealType, ingredients, userEmail
        );
        return ResponseEntity.ok(suggestions);
    }

    @PostMapping("/improve")
    public ResponseEntity<RecipeRequest> improveRecipe(
            @RequestBody RecipeRequest recipe,
            @RequestParam String improvementType,
            Authentication authentication) {
        RecipeRequest improved = aiRecipeService.improveRecipe(recipe, improvementType);
        return ResponseEntity.ok(improved);
    }

    @GetMapping("/ingredient-ideas")
    public ResponseEntity<List<String>> getIngredientIdeas(
            @RequestParam String baseIngredient,
            @RequestParam(required = false) String cuisine) {
        List<String> ideas = aiRecipeService.getIngredientCombinations(baseIngredient, cuisine);
        return ResponseEntity.ok(ideas);
    }
}