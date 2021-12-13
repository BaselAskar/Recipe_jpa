package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.form.RecipeForm;
import com.example.recipe_jpa.model.entities.Recipe;

import java.util.List;

public interface RecipeCreation {

    Recipe save(RecipeForm recipeForm);

    Recipe findById(int id);

    List<Recipe> findAll();

    void delete(int id);
}
