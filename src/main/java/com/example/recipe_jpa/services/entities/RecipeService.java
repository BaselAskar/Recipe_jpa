package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.view.RecipeDTO;
import com.example.recipe_jpa.model.dto.form.RecipeForm;

import java.util.List;
import java.util.Set;

public interface RecipeService extends GenericCRUD<RecipeForm, RecipeDTO,Integer> {

    List<RecipeDTO> findByRecipeName(String recipeName);
    List<RecipeDTO> findByIngredientName(String ingredientName);
    List<RecipeDTO> findByCategory(String category);
    Set<RecipeDTO> findByCategories(String... categories);
}
