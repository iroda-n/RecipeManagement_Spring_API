package com.promineotech.recipes.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Steps {
  private Long stepId;
  private Long recipeId;
  private String stepDescription;
  private int stepNumber;
}
