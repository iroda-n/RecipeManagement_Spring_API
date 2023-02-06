package com.promineotech.recipes.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipesInput {

  private Long recipeId;
  private String recipeName;
  private BigDecimal prepTime;
  private BigDecimal cookTime;
  private int servings;
  
  public boolean isValid() {
    return getRecipeId() != null &&
           getRecipeName() != null && (! getRecipeName().isEmpty());
  }
}
