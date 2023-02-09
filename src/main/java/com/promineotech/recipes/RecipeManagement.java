package com.promineotech.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.promineotech"})
public class RecipeManagement {

  //main method to run application
  public static void main(String[] args) {
    SpringApplication.run(RecipeManagement.class, args);
  } //end main

} //end RecipeManagement class
