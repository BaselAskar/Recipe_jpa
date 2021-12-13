package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.form.RecipeInstructionForm;
import com.example.recipe_jpa.model.entities.RecipeInstruction;

import java.util.List;

public interface RecipeInstructionCreation {

    RecipeInstruction save(RecipeInstructionForm recipeInstructionForm);

    RecipeInstruction findById(String id);

    List<RecipeInstruction> findAll();

    void delete(String id);
}
