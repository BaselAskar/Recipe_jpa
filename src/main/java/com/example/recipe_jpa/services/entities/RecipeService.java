package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.facade.RecipeDTO;
import com.example.recipe_jpa.model.dto.form.RecipeForm;

public interface RecipeService extends GenericCRUD<RecipeForm, RecipeDTO,Integer> {
}
