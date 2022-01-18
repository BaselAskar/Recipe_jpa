package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.facade.RecipeInstructionDto;
import com.example.recipe_jpa.model.dto.form.RecipeInstructionForm;

public interface RecipeInstructionService extends GenericCRUD<RecipeInstructionForm, RecipeInstructionDto,String> {
}
