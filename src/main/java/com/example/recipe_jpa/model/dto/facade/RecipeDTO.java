package com.example.recipe_jpa.model.dto.facade;

import java.io.Serializable;
import java.util.List;

public class RecipeDTO implements Serializable {

    private int id;
    private String recipeName;
    private List<RecipeIngredientDTO> recipeIngredientDTOS;

    public RecipeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredientDTO> getRecipeIngredientDTOS() {
        return recipeIngredientDTOS;
    }

    public void setRecipeIngredientDTOS(List<RecipeIngredientDTO> recipeIngredientDTOS) {
        this.recipeIngredientDTOS = recipeIngredientDTOS;
    }
}
