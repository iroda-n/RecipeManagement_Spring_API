package com.promineotech.recipes.entity;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipes {

  private Long recipeId;
  private String recipeName;
  private BigDecimal prepTime;
  private BigDecimal cookTime;
  private int servings;
  
  private List<Ingredients> ingredients = new LinkedList<>();
  private List<Steps> steps = new LinkedList<>();
  private List<Category> categories = new LinkedList<>();
  
  @JsonIgnore
  public Long getrecipeId () {
    return recipeId;
  }
}
