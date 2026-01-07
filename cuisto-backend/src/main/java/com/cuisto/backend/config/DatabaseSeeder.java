package com.cuisto.backend.config;

import com.cuisto.backend.model.Recipe;
import com.cuisto.backend.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(RecipeRepository recipeRepository) {
        return args -> {
            // Check if database is already populated
            if (recipeRepository.count() > 0) {
                log.info("Database already contains {} recipes", recipeRepository.count());
                return;
            }

            log.info("Seeding database with sample recipes...");

            // Recipe 1: Tunisian Couscous
            Recipe couscous = new Recipe();
            couscous.setTitle("Traditional Tunisian Couscous");
            couscous.setDescription("A classic North African dish with fluffy semolina and vegetables");
            couscous.setImageUrl("https://images.unsplash.com/photo-1637806930600-37fa8892069d");
            couscous.setCuisine("Tunisian");
            couscous.setMealType("lunch");
            couscous.setDietaryTags(new HashSet<>(Arrays.asList("vegetarian")));
            couscous.setDifficulty("medium");
            couscous.setPrepTimeMinutes(20);
            couscous.setCookTimeMinutes(40);
            couscous.setServings(6);

            Recipe.Ingredient ing1 = new Recipe.Ingredient();
            ing1.setName("Couscous");
            ing1.setAmount("500");
            ing1.setUnit("g");

            Recipe.Ingredient ing2 = new Recipe.Ingredient();
            ing2.setName("Chickpeas");
            ing2.setAmount("200");
            ing2.setUnit("g");

            Recipe.Ingredient ing3 = new Recipe.Ingredient();
            ing3.setName("Carrots");
            ing3.setAmount("3");
            ing3.setUnit("pieces");

            couscous.setIngredients(Arrays.asList(ing1, ing2, ing3));
            couscous.setInstructions(Arrays.asList(
                    "Soak the couscous in water",
                    "Prepare the vegetables",
                    "Cook everything in the couscousier",
                    "Serve hot with harissa"
            ));

            Recipe.Nutrition nutrition1 = new Recipe.Nutrition();
            nutrition1.setCalories(450);
            nutrition1.setProtein(15.0);
            nutrition1.setCarbs(75.0);
            nutrition1.setFat(8.0);
            nutrition1.setFiber(10.0);
            couscous.setNutrition(nutrition1);

            // Recipe 2: Italian Pasta Carbonara
            Recipe carbonara = new Recipe();
            carbonara.setTitle("Classic Pasta Carbonara");
            carbonara.setDescription("Creamy Italian pasta with eggs, cheese, and guanciale");
            carbonara.setImageUrl("https://images.unsplash.com/photo-1612874742237-6526221588e3");
            carbonara.setCuisine("Italian");
            carbonara.setMealType("dinner");
            carbonara.setDietaryTags(new HashSet<>());
            carbonara.setDifficulty("easy");
            carbonara.setPrepTimeMinutes(10);
            carbonara.setCookTimeMinutes(20);
            carbonara.setServings(4);

            Recipe.Ingredient ing4 = new Recipe.Ingredient();
            ing4.setName("Spaghetti");
            ing4.setAmount("400");
            ing4.setUnit("g");

            Recipe.Ingredient ing5 = new Recipe.Ingredient();
            ing5.setName("Eggs");
            ing5.setAmount("4");
            ing5.setUnit("pieces");

            Recipe.Ingredient ing6 = new Recipe.Ingredient();
            ing6.setName("Parmesan");
            ing6.setAmount("100");
            ing6.setUnit("g");

            carbonara.setIngredients(Arrays.asList(ing4, ing5, ing6));
            carbonara.setInstructions(Arrays.asList(
                    "Cook pasta in salted water",
                    "Mix eggs and grated cheese",
                    "Combine hot pasta with egg mixture",
                    "Serve immediately with black pepper"
            ));

            Recipe.Nutrition nutrition2 = new Recipe.Nutrition();
            nutrition2.setCalories(550);
            nutrition2.setProtein(20.0);
            nutrition2.setCarbs(65.0);
            nutrition2.setFat(22.0);
            nutrition2.setFiber(3.0);
            carbonara.setNutrition(nutrition2);

            // Recipe 3: Shakshuka
            Recipe shakshuka = new Recipe();
            shakshuka.setTitle("Shakshuka");
            shakshuka.setDescription("Middle Eastern breakfast with eggs poached in tomato sauce");
            shakshuka.setImageUrl("https://images.unsplash.com/photo-1587486937695-ff2021e2b023");
            shakshuka.setCuisine("Middle Eastern");
            shakshuka.setMealType("breakfast");
            shakshuka.setDietaryTags(new HashSet<>(Arrays.asList("vegetarian", "gluten-free")));
            shakshuka.setDifficulty("easy");
            shakshuka.setPrepTimeMinutes(10);
            shakshuka.setCookTimeMinutes(25);
            shakshuka.setServings(4);

            Recipe.Ingredient ing7 = new Recipe.Ingredient();
            ing7.setName("Eggs");
            ing7.setAmount("6");
            ing7.setUnit("pieces");

            Recipe.Ingredient ing8 = new Recipe.Ingredient();
            ing8.setName("Tomatoes");
            ing8.setAmount("800");
            ing8.setUnit("g");

            Recipe.Ingredient ing9 = new Recipe.Ingredient();
            ing9.setName("Bell Peppers");
            ing9.setAmount("2");
            ing9.setUnit("pieces");

            shakshuka.setIngredients(Arrays.asList(ing7, ing8, ing9));
            shakshuka.setInstructions(Arrays.asList(
                    "Sauté peppers and onions",
                    "Add tomatoes and spices",
                    "Make wells and crack eggs",
                    "Cover and cook until eggs are set"
            ));

            Recipe.Nutrition nutrition3 = new Recipe.Nutrition();
            nutrition3.setCalories(220);
            nutrition3.setProtein(14.0);
            nutrition3.setCarbs(15.0);
            nutrition3.setFat(12.0);
            nutrition3.setFiber(4.0);
            shakshuka.setNutrition(nutrition3);

            // Save all recipes
            List<Recipe> recipes = Arrays.asList(couscous, carbonara, shakshuka);
            recipeRepository.saveAll(recipes);

            log.info("Successfully seeded {} recipes", recipes.size());
        };
    }
}