package com.promineotech.recipes.service;

import java.math.BigDecimal;
import java.util.List;
import com.promineotech.recipes.entity.Category;
import com.promineotech.recipes.entity.Ingredients;
import com.promineotech.recipes.entity.Recipes;
import com.promineotech.recipes.entity.RecipesInput;
import com.promineotech.recipes.entity.Steps;

public interface RecipeManagementService {
  Recipes fetchRecipes(Long recipeId);
  List<Recipes> fetchAllRecipes();
  Recipes createRecipe(RecipesInput input);
  Recipes updateRecipe(Long recipeId, String recipeName, BigDecimal prepTime, BigDecimal cookTime, int servings);
  Recipes deleteRecipe(Long recipeId);
  List<Ingredients> fetchIngredients(Long recipeId);
  List<Steps> fetchSteps(Long recipeId);
  List<Category> fetchCategoriesForRecipes(Long recipeId);
}
