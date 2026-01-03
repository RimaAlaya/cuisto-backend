package com.cuisto.backend.repository;

import com.cuisto.backend.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByCuisine(String cuisine);
    List<Recipe> findByMealType(String mealType);
    List<Recipe> findByDietaryTagsContaining(String tag);
    List<Recipe> findByAuthorId(String authorId);
}