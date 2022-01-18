package com.example.recipe_jpa.model.dto.facade;

import java.util.ArrayList;
import java.util.List;

public class RecipeCategoryDto {


    private int id;
    private String category;
    private List<RecipeDTO> recipes;

    public RecipeCategoryDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }
}
