-- Recipes
INSERT INTO recipes (recipe_id, recipe_name, prep_time, cook_time, servings) VALUES(1, 'Lemon Roll', 20, 40, 10);
INSERT INTO recipes (recipe_id, recipe_name, prep_time, cook_time, servings) VALUES(2, 'Chocolate Chip Cookies', 15, 10, 12);
INSERT INTO recipes (recipe_id, recipe_name, prep_time, cook_time, servings) VALUES(3, 'Roasted Tomato Basil Soup', 15, 95, 6);

-- Category
INSERT INTO category (category_id, category_name) VALUES(1, 'Cookies');
INSERT INTO category (category_id, category_name) VALUES(2, 'Pastries');
INSERT INTO category (category_id, category_name) VALUES(3, 'Soups');

-- Recipes Category 
INSERT INTO recipes_category (recipe_id, category_id) VALUES(1, 2);
INSERT INTO recipes_category (recipe_id, category_id) VALUES(2, 1);
INSERT INTO recipes_category (recipe_id, category_id) VALUES(3, 3);

-- Steps Lemon Roll
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(1, 1, 'Preheat the oven to 355 degrees.', 1);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(2, 1, 'Beat eggs and sugar with with mixer on high speed until sugar is dissolved. Mixture should increase in volume.', 2);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(3, 1, 'Mix in sifted flour with spatula dividing it in half.', 3);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(4, 1, 'Distribute mix on a baking sheet. Bake at 355 degrees for 15-20 minutes.', 4);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(5, 1, 'Wash lemons and cut them removing all seeds. Using blender, blend lemons with sugar until smooth.', 5);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(6, 1, 'Remove biscuit roll from the oven and take off baking sheet immediately.', 6);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(7, 1, 'Spread lemon blend evenly on the biscuit and roll it before it gets cold.', 7);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(8, 1, 'Let the roll cool down completely before serving.', 8);

-- Steps Chocolate Chip Cookies
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(9, 2, 'Preheat the oven to 375 degrees.', 1);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(10, 2, 'Combine flour, baking soda, and salt in a small bowl.', 2);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(11, 2, 'Beat butter, sugar, brown sugar, and vanilla in a large mixing bowl.', 3);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(12, 2, 'Add eggs 1 at a time beating well.', 4);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(13, 2, 'Gradually beat in flour mixture. Stir in chocolate chips.', 5);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(14, 2, 'Drop by round tablespoon onto ungreased cookie sheet.', 6);
INSERT INTO steps (step_id, recipe_id, step_description, step_number) VALUES(15, 2, 'Bake for 9-11 minutes until golden brown. Cool for 2 minutes.', 7);

-- Ingredients Lemon Roll
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(1, 1, 'Eggs', '4');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(2, 1, 'Flour', '100g');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(3, 1, 'Sugar', '100g');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(4, 1, 'Lemon', '4');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(5, 1, 'Sugar', '150g');

-- Ingredients Chocolate Chip Cookies
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(6, 2, 'Flour', '2 1/4 cups');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(7, 2, 'Baking Soda', '1tsp');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(8, 2, 'Salt', '1tsp');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(9, 2, 'Softened butter', '1 cup');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(10, 2, 'Granulated Sugar', '3/4 cup');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(11, 2, 'Brown Sugar', '3/4 cup');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(12, 2, 'Vanila Extract', '1tsp');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(13, 2, 'Eggs', '2');
INSERT INTO ingredients (ingredient_id, recipe_id, ingredient_name, ingredient_quantity) VALUES(14, 2, 'Chocolate Chips', '2 cup');