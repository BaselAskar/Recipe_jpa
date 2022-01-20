package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.view.RecipeIngredientDTO;
import com.example.recipe_jpa.model.dto.form.RecipeIngredientForm;

public interface RecipeIngredientService extends GenericCRUD<RecipeIngredientForm, RecipeIngredientDTO,String> {
}
