package com.promineotech.recipes.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.recipes.entity.Category;
import com.promineotech.recipes.entity.Ingredients;
import com.promineotech.recipes.entity.Recipes;
import com.promineotech.recipes.entity.RecipesInput;
import com.promineotech.recipes.entity.Steps;
import com.promineotech.recipes.service.RecipeManagementService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultRecipeManagementController implements RecipeManagementController {
  
  //commented out because it causes program to not compile
  //@Autowired
  private RecipeManagementService recipeManagementService;
  
  /**
   * @param recipeId
   * return a recipe using user-entered recipeId
   */
  @Override
  public Recipes fetchRecipes(@PathVariable Long recipeId) {
    log.info("recipeName={}", recipeId);
    if (recipeId != null) {
      Recipes recipe = recipeManagementService.fetchRecipes(recipeId);
      if (recipe != null) {
        return recipe;
      }
      
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                        String.format("Recipe (%s) not found.", recipeId));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe not provided.");
  } //end fetchRecipes
          
  /**
   * return all recipes in database
   */
  @Override
  public List<Recipes> allRecipes() {
     List<Recipes> recipes = recipeManagementService.fetchAllRecipes();
     return recipes;
  } //end allRecipes

  /**
   * Create a recipe using recipe name provided by user
   */
  @Override
  public Recipes createRecipe(@RequestBody RecipesInput input) {
    log.info("New Recipe={}", input);
    if ((input != null) && (input.isValid())) {
      Recipes recipe = recipeManagementService.createRecipe(input);
      if (recipe != null) {
        return recipe;
      }
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unhandled exception occured. Recipe not created.");
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid recipe. Fields missing.");
  } //end createRecipe

  /**
   * update recipe using user-provided recipeId
   */
  @Override
  public Recipes updateRecipe(Long recipeId, String recipeName, BigDecimal prepTime, BigDecimal cookTime, int servings) {
    if ((recipeName != null) && (!recipeName.isEmpty())) {
      Recipes recipe = recipeManagementService.updateRecipe(recipeId, recipeName, prepTime, cookTime, servings);
      if (recipe != null) {
        return recipe;
      }
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unhandled exception occured. Country not created.");
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid country. Fields missing.");
  } //end updateRecipe


  /**
   * delete specified recipe
   */
  @Override
  public Recipes deleteRecipe(@PathVariable Long recipeId) {
    if (recipeId != null) {
      Recipes existing = recipeManagementService.fetchRecipes(recipeId);
      if (existing != null) {
        Recipes recipe = recipeManagementService.deleteRecipe(recipeId);
        if (recipe != null) {
          return recipe;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unhandled exception occured. Recipe not deleted.");
      }
      
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The requested recipe (%s) does not exist.", recipeId));
    }
    
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid recipe name. Value: %s", recipeId));
  } //end deleteRecipe

  /**
   * retrieve ingredients for a recipe using recipeId
   */
  @Override
  public List<Ingredients> fetchIngredients(Long recipeId) {
    log.info("recipeId={}", recipeId);
    if (recipeId != null) {
      List<Ingredients> ingredients = recipeManagementService.fetchIngredients(recipeId);
      if (ingredients != null) {
        return ingredients;
      }
      
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                        String.format("Ingredients for recipe Id (%s) not found.", recipeId));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe Id not provided.");       
  } //end fetchIngredients
  
  /**
   * retrieve steps for a specified recipe using recipeId
   */
  @Override
  public List<Steps> fetchSteps(Long recipeId) {
    log.info("recipeId={}", recipeId);
    if (recipeId != null) {
      List<Steps> steps = recipeManagementService.fetchSteps(recipeId);
      if (steps != null) {
        return steps;
      }
      
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                        String.format("Steps for recipe Id (%s) not found.", recipeId));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe Id not provided.");       
  } //end fetchSteps
 
  /**
   * retrieve category for a specified recipe using recipeId
   */
  @Override
  public List<Category> fetchCategories(Long recipeId) {
    log.info("recipeId={}", recipeId);
    if (recipeId != null) {
      List<Category> categories = recipeManagementService.fetchCategoriesForRecipes(recipeId);
      if (categories != null) {
        return categories;
      }
      
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                                        String.format("Steps for recipe Id (%s) not found.", recipeId));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe Id not provided.");       
  } //end fetchCategories
  
} //end DefaultRecipeManagementController class
