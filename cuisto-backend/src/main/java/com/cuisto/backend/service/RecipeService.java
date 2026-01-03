package com.cuisto.backend.service;

import com.cuisto.backend.dto.RecipeRequest;
import com.cuisto.backend.model.Recipe;
import com.cuisto.backend.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Recipe createRecipe(RecipeRequest request, String authorId) {
        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setCuisine(request.getCuisine());
        recipe.setMealType(request.getMealType());
        recipe.setDietaryTags(request.getDietaryTags());
        recipe.setDifficulty(request.getDifficulty());
        recipe.setPrepTimeMinutes(request.getPrepTimeMinutes());
        recipe.setCookTimeMinutes(request.getCookTimeMinutes());
        recipe.setServings(request.getServings());
        recipe.setIngredients(request.getIngredients());
        recipe.setInstructions(request.getInstructions());
        recipe.setNutrition(request.getNutrition());
        recipe.setAuthorId(authorId);

        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(String id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public List<Recipe> getRecipesByCuisine(String cuisine) {
        return recipeRepository.findByCuisine(cuisine);
    }

    public List<Recipe> getRecipesByMealType(String mealType) {
        return recipeRepository.findByMealType(mealType);
    }

    public List<Recipe> getRecipesByDietaryTag(String tag) {
        return recipeRepository.findByDietaryTagsContaining(tag);
    }

    public Recipe updateRecipe(String id, RecipeRequest request) {
        Recipe recipe = getRecipeById(id);

        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setCuisine(request.getCuisine());
        recipe.setMealType(request.getMealType());
        recipe.setDietaryTags(request.getDietaryTags());
        recipe.setDifficulty(request.getDifficulty());
        recipe.setPrepTimeMinutes(request.getPrepTimeMinutes());
        recipe.setCookTimeMinutes(request.getCookTimeMinutes());
        recipe.setServings(request.getServings());
        recipe.setIngredients(request.getIngredients());
        recipe.setInstructions(request.getInstructions());
        recipe.setNutrition(request.getNutrition());
        recipe.setUpdatedAt(LocalDateTime.now());

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(String id) {
        recipeRepository.deleteById(id);
    }
}