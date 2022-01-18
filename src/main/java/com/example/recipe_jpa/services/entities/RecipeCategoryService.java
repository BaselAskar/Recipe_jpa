package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.model.dto.facade.RecipeCategoryDto;
import com.example.recipe_jpa.model.dto.form.RecipeCategoryFrom;
import com.example.recipe_jpa.model.entities.RecipeCategory;

public interface RecipeCategoryService extends GenericCRUD<RecipeCategoryFrom, RecipeCategoryDto,Integer>{
}
