package com.promineotech.recipes.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredients {
  private Long ingredientId;
  private Long recipeId;
  private String ingredientName;
  private String ingredientQuantity;

}
