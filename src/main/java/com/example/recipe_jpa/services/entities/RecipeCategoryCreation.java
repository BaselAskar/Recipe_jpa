package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.form.RecipeCategoryFrom;
import com.example.recipe_jpa.model.entities.RecipeCategory;

import java.util.List;

public interface RecipeCategoryCreation {

    RecipeCategory save(RecipeCategoryFrom recipeCategoryFrom);

    RecipeCategory findById(int id);

    List<RecipeCategory> findAll();

    void delete(int id);
}
