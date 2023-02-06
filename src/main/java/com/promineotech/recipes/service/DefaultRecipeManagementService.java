package com.promineotech.recipes.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.recipes.dao.RecipeManagementDao;
import com.promineotech.recipes.entity.Recipes;
import com.promineotech.recipes.entity.RecipesInput;
import com.promineotech.recipes.entity.Steps;
import com.promineotech.recipes.entity.Category;
import com.promineotech.recipes.entity.Ingredients;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
public abstract class DefaultRecipeManagementService implements RecipeManagementService {
  
  @Autowired
  private RecipeManagementDao recipeManagementDao;

  /**
   * retrieve recipe based on recipeId
   */
  @Override
  public Recipes fetchRecipes(Long recipeId) {
    log.info("The fetchRecipes method was called with recipe={}", recipeId);
    if (recipeId != null) {
      Optional<Recipes> recipe = recipeManagementDao.fetchRecipes(recipeId);
      
      //I tried to add all information for recipe in one place, to do later
      //Optional<Ingredients> ingredients = recipeManagementDao.fetchIngredients(recipeId);
      //Optional<Category> category = recipeManagementDao.fetchCategoriesForRecipes(recipeId);
      //Optional<Steps> steps = recipeManagementDao.fetchSteps(recipeId);
      //Optional<T> list = recipe, ingredients, category, steps; 
      
      if (recipe.isPresent()) {
        return recipe.get();
        //return recipeManagementDao.displayRecipe(recipe, ingredients, category, steps);
        
      }
    }
    return null;
  } //end fetchRecipes

  /**
   * retrieve all recipes
   */
  @Override
  public List<Recipes> fetchAllRecipes() {
    Stream<Recipes> recipes = recipeManagementDao.fetchAllRecipes();
    return recipes.toList();
  } //end fetchAllRecipes

  /**
   * create recipe based on input provided by user
   */
  @Override
  public Recipes createRecipe(RecipesInput input) {
    if ((input != null) && (input.isValid())) {
      Optional<Recipes> recipe = recipeManagementDao.createRecipe(input);
      if (recipe.isPresent()) {
        return recipe.get();
      }
    }
    return null;
  } //end createRecipe

  /**
   * update recipe information based on user input
   */
  @Override
  public Recipes updateRecipe (Long recipeId, String recipeName, BigDecimal prepTime, BigDecimal cookTime, int servings) {
    if ((recipeName != null) && (!recipeName.isEmpty())) {
      Optional<Recipes> recipe = recipeManagementDao.updateRecipe(recipeId, recipeName, prepTime, cookTime, servings);
      if (recipe.isPresent()) {
        return recipe.get();
      }
    }
    return null;
  } //end updateRecipe

  /**
   * delete recipe using recipeId
   */
  @Override
  public Recipes deleteRecipe(Long recipeId) {
    if (recipeId != null) {
      Optional<Recipes> recipe = recipeManagementDao.deleteRecipe(recipeId);
      if (recipe.isPresent()) {
        return recipe.get();
      }
    }
    return null;
 } //end deleteRecipe
  
  /**
   * retrieve ingredients using recipeId
   */
  @Override
  public List<Ingredients> fetchIngredients(Long recipeId) {
    log.info("The fetchIngredients method was called with recipe={}", recipeId);
    if (recipeId != null) {
      List<Ingredients> ingredients = recipeManagementDao.fetchIngredients(recipeId);
      if (!ingredients.isEmpty()) {
        return ingredients; 
      }
    }
    return null;
  } //end fetchIngredients
  
  /**
   * retrieve steps for a specified recipe
   */
  @Override
  public List<Steps> fetchSteps(Long recipeId) {
    log.info("The fetchSteps method was called with recipe={}", recipeId);
    if (recipeId != null) {
      List<Steps> steps = recipeManagementDao.fetchSteps(recipeId);
      if (!steps.isEmpty()) {
        return steps; 
      }
    }
    return null;
  } //end fetchSteps
  
  /**
   * retrieve category for specified recipe
   */
  @Override
  public List<Category> fetchCategoriesForRecipes(Long recipeId) {
    log.info("The fetchCategories method was called with recipe={}", recipeId);
    if (recipeId != null) {
      List<Category> categories = recipeManagementDao.fetchCategoriesForRecipes(recipeId);
      if (!categories.isEmpty()) {
        return categories; 
      }
    }
    return null;
  } //end fetchCategoriesForRecipes
} //end DefaultRecipeManagementService class
