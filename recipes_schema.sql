DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS steps;
DROP TABLE IF EXISTS recipes_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes(
  recipe_id INT AUTO_INCREMENT NOT NULL,
  recipe_name VARCHAR(128) NOT NULL,
  prep_time DECIMAL(7,2),
  cook_time DECIMAL(7,2),
  servings INT,
  PRIMARY KEY (recipe_id)
);

CREATE TABLE category(
  category_id INT AUTO_INCREMENT NOT NULL,
  category_name VARCHAR(128) NOT NULL,
  PRIMARY KEY (category_id)
);

CREATE TABLE recipes_category(
  recipe_id INT NOT NULL,
  category_id INT NOT NULL,
  FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id) ON DELETE CASCADE,
  FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
  UNIQUE KEY (recipe_id, category_id)
);

create TABLE steps(
  step_id INT AUTO_INCREMENT NOT NULL,
  recipe_id INT NOT NULL,
  step_description TEXT NOT NULL,
  step_number INT NOT NULL,
  PRIMARY KEY (step_id),
  FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id) ON DELETE CASCADE
);

CREATE TABLE ingredients(
  ingredient_id INT AUTO_INCREMENT NOT NULL,
  recipe_id INT NOT NULL,
  ingredient_name VARCHAR(128) NOT NULL,
  ingredient_quantity VARCHAR(128) NOT NULL,
  PRIMARY KEY (ingredient_id),
  FOREIGN KEY (recipe_id) REFERENCES recipes (recipe_id) ON DELETE CASCADE
);
