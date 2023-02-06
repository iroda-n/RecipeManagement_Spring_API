package com.promineotech.recipes.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import com.promineotech.recipes.entity.Category;
import com.promineotech.recipes.entity.Ingredients;
import com.promineotech.recipes.entity.Recipes;
import com.promineotech.recipes.entity.RecipesInput;
import com.promineotech.recipes.entity.Steps;

public interface RecipeManagementDao {
  
  Optional<Recipes> fetchRecipes(Long recipeId);
  Stream<Recipes> fetchAllRecipes();
  Optional<Recipes> createRecipe(RecipesInput input);
  Optional<Recipes> updateRecipe(Long recipeId, String recipeName, BigDecimal prepTime, BigDecimal cookTime, int servings);
  Optional<Recipes> deleteRecipe(Long recipeId);
  List<Category> fetchCategoriesForRecipes(Long recipeId);
  List<Ingredients> fetchIngredients(Long recipeId);
  List<Steps> fetchSteps(Long recipeId);
  //Optional<Recipes> displayRecipe(Optional<Recipes> recipe, Optional<Ingredients> ingredients, Optional<Category> category, Optional<Steps> steps);
  
}
