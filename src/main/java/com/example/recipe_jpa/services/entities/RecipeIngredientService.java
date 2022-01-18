package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.facade.RecipeIngredientDTO;
import com.example.recipe_jpa.model.dto.form.RecipeIngredientForm;

public interface RecipeIngredientService extends GenericCRUD<RecipeIngredientForm, RecipeIngredientDTO,String> {
}
