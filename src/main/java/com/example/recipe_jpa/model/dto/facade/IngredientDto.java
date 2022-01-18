package com.example.recipe_jpa.model.dto.facade;

public class IngredientDto {

    private int id;
    private String ingredientName;

    public IngredientDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
