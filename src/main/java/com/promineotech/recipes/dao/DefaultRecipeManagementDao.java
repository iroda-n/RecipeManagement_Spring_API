package com.promineotech.recipes.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import com.promineotech.recipes.entity.Category;
//import com.promineotech.recipes.entity.Ingredients;
import com.promineotech.recipes.entity.Recipes;
import com.promineotech.recipes.entity.RecipesInput;
//import com.promineotech.recipes.entity.Steps;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Service
public class DefaultRecipeManagementDao implements RecipeManagementDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  private Recipes toRecipes(ResultSet rs, int rowNum) {
    try {
      Recipes recipe = new Recipes(rs.getLong("recipe_id"), rs.getString("recipe_name"), 
                       new BigDecimal(rs.getString("prep_time")), new BigDecimal(rs.getString("cook_time")),
                       (rs.getInt("servings")));
      return recipe;
    } catch (SQLException e) {
      return null;
    }
  }

  @Override
  public Optional<Recipes> fetchRecipes(Long recipeId) {
    log.info("DAO: recipe={}", recipeId);
    if (recipeId != null) {
      String sql = "SELECT * "
        + "FROM recipes "
        + "WHERE recipe_id = :recipe_id";
    
      Map<String, Object> params = new HashMap<>();
      params.put("recipe_id", recipeId);
    
      List<Recipes> recipes = jdbcTemplate.query(sql, params, (rs,rowNum) -> {
        return toRecipes(rs, rowNum);
      });
      if (recipes.size() >= 1) {
        return Optional.of(recipes.get(0));
      }
    }
    return Optional.empty();
  }
    
  
  @Override
  public Stream<Recipes> fetchAllRecipes () {
    String sql = "SELECT recipe_id,recipe_name,prep_time,cook_time,servings "
        + "FROM recipes;";
    
    Map<String,Object> params = new HashMap<>();
    
    List<Recipes> recipes = jdbcTemplate.query(sql, params,
        new RowMapper<>() {

          @Override
          public Recipes mapRow(ResultSet rs, int rowNum) throws SQLException {
            
            return Recipes.builder()
                .recipeId(rs.getLong("recipe_id"))
                .recipeName(rs.getString("recipe_name"))
                .prepTime(new BigDecimal(rs.getString("prep_time")))
                .cookTime(new BigDecimal(rs.getString("cook_time")))
                .servings(rs.getInt("servings"))
                .build();
          }});
    
    return recipes.stream();
  }


  @Override
  public Optional<Recipes> createRecipe(RecipesInput input) {
    if ((input != null) && (input.isValid())) {
      String sql = "INSERT INTO recipes (recipe_id,recipe_name,prep_time,cook_time,servings) "
                 + "VALUES (:recipe_id,:recipe_name,:prep_time,:cook_time,:servings);";
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("recipe_id", input.getRecipeId());
      parameters.put("recipe_name", input.getRecipeName());
      parameters.put("prep_time", input.getPrepTime());
      parameters.put("cook_time", input.getCookTime());
      parameters.put("servings", input.getServings());

      
      int rows = jdbcTemplate.update(sql, parameters);
      
      if (rows == 1) {
        return fetchRecipes(input.getRecipeId());
      }
    }
    return Optional.empty();
  }

  @Override
  public Optional<Recipes> updateRecipe(Long recipeId, String recipeName, BigDecimal prepTime, BigDecimal cookTime, int servings) {
    Optional<Recipes> existing = fetchRecipes(recipeId);
    if (existing.isPresent()) {
      String sql = "UPDATE recipes "
          + "SET recipe_name = :recipe_name,prep_time = :prep_time,cook_time = :cook_time,servings=:servings "
          + "WHERE recipe_name = :recipe_name;";
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("recipe_name", recipeName);
      parameters.put("prep_time", prepTime);
      parameters.put("cook_time", cookTime);
      parameters.put("servings", servings);
      
      int rows = jdbcTemplate.update(sql, parameters);
      
      if (rows == 1) {
        return Optional.ofNullable(Recipes.builder()
            .recipeName(recipeName)
            .prepTime(prepTime)
            .cookTime(cookTime)
            .servings(servings)
            .build());
      }
    }
    return Optional.empty();
  }

  @Override
  public Optional<Recipes> deleteRecipe(Long recipeId) {
    Optional<Recipes> existing = fetchRecipes(recipeId);
    if (existing.isPresent()) {
      String sql = "DELETE FROM recipes WHERE recipe_id = :recipe_id;";
      Map<String,Object> parameters = new HashMap<>();
      parameters.put("recipe_id", recipeId);
      
      int rows = jdbcTemplate.update(sql, parameters);
      if (rows == 1) {
       return Optional.ofNullable(Recipes.builder()
            .recipeId(recipeId)
            .build());
      }
    }
    return Optional.empty();
  }
  
  /*
  @Override
  public List<Category> fetchCategoriesForRecipes(Long recipeId) {
    // @formatter:off
    String sql = "SELECT category_name FROM category c " 
        + " JOIN recipes_category rc USING (category_id) "
        + "WHERE recipe_id = :recipe_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("recipe_id", recipeId);

    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
          
          return Category.builder()
              .categoryId(rs.getLong("category_id"))
              .categoryName(rs.getString("category_name"))
              .build();
        }});
  }
  */
/*
  @Override
  public List<Ingredients> fetchIngredients(Long recipeId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM ingredients " 
        + "WHERE recipe_id = :recipe_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("recipe_id", recipeId);

    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
        @Override
        public Ingredients mapRow(ResultSet rs, int rowNum) throws SQLException {
          
          return Ingredients.builder()
              .ingredientId(rs.getLong("ingredient_id"))
              .ingredientName(rs.getString("ingredient_name"))
              .ingredientQuantity(rs.getString("ingredient_quantity"))
              .build();
        }});
  }*/

  /*
  @Override
  public List<Steps> fetchSteps(Long recipeId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM steps " 
        + "WHERE recipe_id = :recipe_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("recipe_id", recipeId);

    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
        @Override
        public Steps mapRow(ResultSet rs, int rowNum) throws SQLException {
          
          return Steps.builder()
              .stepId(rs.getLong("step_id"))
              .stepNumber(rs.getInt("step_number"))
              .stepDescription(rs.getString("step_description"))
              .build();
        }});
  }
  */
  /*@Override
  public Optional<Recipes> displayRecipe(Optional<Recipes> recipe,
      Optional<Ingredients> ingredients, Optional<Category> category, Optional<Steps> steps) {
    
    return recipe.builder()
        .ingredients(ingredients)
        .category(category)
        .steps(steps)
        .build();
  }*/
  
}
