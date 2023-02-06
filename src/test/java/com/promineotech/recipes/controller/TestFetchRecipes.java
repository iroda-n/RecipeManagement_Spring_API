package com.promineotech.recipes.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.recipes.entity.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/recipes_schema.sql",
    "classpath:flyway/migrations/recipes_data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))
class TestFetchRecipes {
  @Autowired
  private TestRestTemplate restTemplate;
  
  @LocalServerPort
  private int serverPort;
  
  @Test
  void testThatRecipesAreReturnedWhenAValidNameIsSupplied() {
    //Given: a valid recipeName and URI
    String recipeName = "Lemon Roll";
    String uri = String.format("http://localhost:%d/recipes?recipeName=%s",serverPort, recipeName);
    
    //When: a connection is made to URI
    ResponseEntity<List<Recipes>> response = restTemplate.exchange(uri, HttpMethod.GET, null, 
                                                                new ParameterizedTypeReference<>() {});
   
    //Then: a success (200 - OK) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    //And: the actual list returned is the same as the expected list
    List<Recipes> expected = buildExpected();
    assertThat(response.getBody()).isEqualTo(expected);
  }
  
  protected List<Recipes> buildExpected() {
    List<Recipes> list = new ArrayList<>();
    
    list.add(Recipes.builder()
        .recipeName("Lemon Roll")
        .prepTime(new BigDecimal("20"))
        .cookTime(new BigDecimal("40"))
        .servings(10)

        .build());
    
    return list;
    
  }

}
