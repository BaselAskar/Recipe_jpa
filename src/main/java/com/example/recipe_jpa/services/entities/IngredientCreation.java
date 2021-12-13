package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.form.IngredientForm;
import com.example.recipe_jpa.model.entities.Ingredient;

import java.util.List;

public interface IngredientCreation {

    Ingredient save(IngredientForm ingredientForm);

    Ingredient findById(int id);

    List<Ingredient> findAll();

    void delete(int id);
}
