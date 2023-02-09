package com.promineotech.recipes.controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.recipes.entity.Category;
import com.promineotech.recipes.entity.Ingredients;
import com.promineotech.recipes.entity.Recipes;
import com.promineotech.recipes.entity.RecipesInput;
import com.promineotech.recipes.entity.Steps;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

//@RequestMapping("/recipes")
@OpenAPIDefinition(info = @Info(title = "Recipe Management Service"), servers = {
      @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface RecipeManagementController {
  //@formatter:off
  @Operation (
      summary = "Returns a Recipe",
      description = "Returns a Recipe given a recipe id "
          + "(1-Lemon Roll, 2-Chocolota chip cookies, 3-Roasted Tomato Basil Soup)",
      responses = { 
          @ApiResponse(
              responseCode = "200", 
              description = "A Recipe is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Recipes were not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeId", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe id "
                  + "(1-Lemon Roll, 2-Chocolota chip cookies, 3-Roasted Tomato Basil Soup)")
        }
      )
  
  
  @GetMapping(value = "/{recipeId}")
  @ResponseStatus(code = HttpStatus.OK)
  Recipes fetchRecipes(
      @RequestParam(required = false) 
      Long recipeId);

  //@formatter:on
  
  @Operation (
      summary = "Returns all available Recipes",
      description = "Returns a list of Recipes",
      responses = { 
          @ApiResponse(
              responseCode = "200", 
              description = "A list of Recipes is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Recipes were found", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeId", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe name (i.e., 'Chocolate Chip Cookies')")
        }
      )
  
  @GetMapping(value = "/recipes")
  @ResponseStatus(code = HttpStatus.OK)
  List<Recipes> allRecipes();
  
  @Operation (
      summary = "Create new Recipe",
      description = "Returns created Recipe",
      responses = { 
          @ApiResponse(
              responseCode = "201", 
              description = "The created Recipe is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Recipe component was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeName", 
              allowEmptyValue = true, 
              required = false, 
              description = " The recipe name input (i.e., 'Chocolate Chip Cookies')")
        }
      )
 
  @PostMapping(value = "/recipes")
  @ResponseStatus(code = HttpStatus.CREATED)
  Recipes createRecipe(@RequestBody RecipesInput input);
  
  @Operation (
      summary = "Update existing Recipe",
      description = "Updates existing Recipe using recipe id"
          + "(1-Lemon Roll, 2-Chocolota chip cookies, 3-Roasted Tomato Basil Soup)",
      responses = { 
          @ApiResponse(
              responseCode = "200", 
              description = "The updated Recipe is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Recipe component was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeName", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe name (i.e., 'Chocolate Chip Cookies')"),
          @Parameter(
              name = "prepTime", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe prep time in minutes(i.e., 15)"),
          @Parameter(
              name = "cookTime", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe cook time in minutes(i.e., 15)"),
          @Parameter(
              name = "servings", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe servings (i.e., 5)")
        }
      )
 
  @PutMapping(value = "/{recipeId}")
  @ResponseStatus(code = HttpStatus.OK)
  Recipes updateRecipe(@RequestParam (required = false) Long recipeId,
      @RequestParam (required = false) String recipeName, 
      @RequestParam (required = false) BigDecimal prepTime, 
      @RequestParam (required = false) BigDecimal cookTime,
      @RequestParam (required = false) int servings);
  
  
  @Operation (
      summary = "Delete a Recipe",
      description = "Delete a Recipe using recipe id"
          + "(1-Lemon Roll, 2-Chocolota chip cookies, 3-Roasted Tomato Basil Soup)",
      responses = { 
          @ApiResponse(
              responseCode = "200", 
              description = "Recipe is deted", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Recipe component was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeName", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe Id "
                  + "(1-Lemon Roll, 2-Chocolota chip cookies, 3-Roasted Tomato Basil Soup)")
      }
    )

  @DeleteMapping(value = "/{recipeId}")
  @ResponseStatus(code = HttpStatus.OK)
  Recipes deleteRecipe(Long recipeId);
  
//@formatter:off
  @Operation (
      summary = "Returns a List of Ingredients for specified Recipe",
      description = "Returns a List of Ingredients given a recipe id "
          + "(1-Lemon Roll, 2-Chocolate chip cookies, 3-Roasted Tomato Basil Soup)",
      responses = { 
          @ApiResponse(
              responseCode = "200", 
              description = "A List of Ingredients is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Ingredients were not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeId", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe id "
                  + "(1-Lemon Roll, 2-Chocolate chip cookies, 3-Roasted Tomato Basil Soup)")
        }
      )
  
  
  @GetMapping(value = "/{recipeid}/ingredients")
  @ResponseStatus(code = HttpStatus.OK)
  List<Ingredients> fetchIngredients(
      @RequestParam(required = false) 
      Long recipeId);

  //@formatter:on

  
//@formatter:off
  @Operation (
      summary = "Returns a List of Steps for specified Recipe",
      description = "Returns a List of Steps given a recipe id "
          + "(1-Lemon Roll, 2-Chocolate chip cookies, 3-Roasted Tomato Basil Soup)",
      responses = { 
          @ApiResponse(
              responseCode = "200", 
              description = "A List of Steps is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Steps were found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeId", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe Id "
                  + "(1-Lemon Roll, 2-Chocolota chip cookies, 3-Roasted Tomato Basil Soup)")
        }
      )
  
  
  @GetMapping(value = "/{recipeid}/steps")
  @ResponseStatus(code = HttpStatus.OK)
  List<Steps> fetchSteps(
      @RequestParam(required = false) 
      Long recipeId);

  //@formatter:on
  
  
//@formatter:off
  @Operation (
      summary = "Returns a List of Categories for specified Recipe",
      description = "Returns a List of Categories given a recipe id "
          + "(1-Lemon Roll, 2-Chocolate chip cookies, 3-Roasted Tomato Basil Soup)",
      responses = { 
          @ApiResponse(
              responseCode = "200", 
              description = "A List of Categories is returned", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Recipes.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Categories were not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occured", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "recipeId", 
              allowEmptyValue = false, 
              required = false, 
              description = " The recipe Id "
                  + "(1-Lemon Roll, 2-Chocolota chip cookies, 3-Roasted Tomato Basil Soup)")
        }
      )
  
  
  @GetMapping(value = "/{recipeid}/categories")
  @ResponseStatus(code = HttpStatus.OK)
  List<Category> fetchCategories(
      @RequestParam(required = false) 
      Long recipeId);

  //@formatter:on 
}


