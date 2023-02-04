package com.promineotech.recipes.service;

import java.util.List;
import com.promineotech.recipes.entity.*;

public interface RecipeManagementService {
  List<Recipes> fetchRecipes(String recipeName);

}
