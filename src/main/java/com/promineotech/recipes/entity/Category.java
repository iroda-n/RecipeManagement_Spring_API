package com.promineotech.recipes.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
  
  private Long categoryId;
  private String categoryName;
}
