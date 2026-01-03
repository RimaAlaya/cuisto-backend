package com.cuisto.backend.controller;

import com.cuisto.backend.dto.RecipeRequest;
import com.cuisto.backend.model.Recipe;
import com.cuisto.backend.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(
            @Valid @RequestBody RecipeRequest request,
            Authentication authentication) {
        String userEmail = authentication.getName();
        Recipe recipe = recipeService.createRecipe(request, userEmail);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("/public/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<List<Recipe>> getRecipesByCuisine(@PathVariable String cuisine) {
        return ResponseEntity.ok(recipeService.getRecipesByCuisine(cuisine));
    }

    @GetMapping("/meal-type/{mealType}")
    public ResponseEntity<List<Recipe>> getRecipesByMealType(@PathVariable String mealType) {
        return ResponseEntity.ok(recipeService.getRecipesByMealType(mealType));
    }

    @GetMapping("/dietary/{tag}")
    public ResponseEntity<List<Recipe>> getRecipesByDietaryTag(@PathVariable String tag) {
        return ResponseEntity.ok(recipeService.getRecipesByDietaryTag(tag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(
            @PathVariable String id,
            @Valid @RequestBody RecipeRequest request) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}