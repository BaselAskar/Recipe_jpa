package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.facade.IngredientDto;
import com.example.recipe_jpa.model.dto.form.IngredientForm;

import java.util.List;

public interface IngredientService extends GenericCRUD<IngredientForm, IngredientDto,Integer> {
    IngredientDto findByIngredientName(String ingredientName);
    List<IngredientDto> searchByIngredientName(String name);
}
