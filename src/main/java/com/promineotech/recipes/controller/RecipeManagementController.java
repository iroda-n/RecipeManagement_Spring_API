package com.promineotech.recipes.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import com.promineotech.recipes.entity.*;

@RequestMapping("/recipes")
@OpenAPIDefinition(info = @Info(title = "Recipe Management Service"), servers = {
      @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface RecipeManagementController {
  //@formatter:off
  @Operation (
      summary = "Returns a list of Recipes",
      description = "Returns a list of Recipes given an optional recipe name",
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
              description = "No Recipes were found with the input criteria", 
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
              description = " The recipe name (i.e., 'Chocolate Chip Cookies')")
        }
      )
  
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Recipes> fetchRecipes(
      @RequestParam(required = false) 
      String recipeName);

  //@formatter:on

}
