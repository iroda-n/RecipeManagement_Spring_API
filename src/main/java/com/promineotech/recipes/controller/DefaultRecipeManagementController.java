package com.promineotech.recipes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.recipes.entity.Recipes;
import com.promineotech.recipes.service.RecipeManagementService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultRecipeManagementController implements RecipeManagementController {
  
  @Autowired
  private RecipeManagementService recipeManagementService;

  @Override
  public List<Recipes> fetchRecipes(String recipeName) {
    log.info("recipeName={}", recipeName);
    return recipeManagementService.fetchRecipes(recipeName);
  }
  

}
