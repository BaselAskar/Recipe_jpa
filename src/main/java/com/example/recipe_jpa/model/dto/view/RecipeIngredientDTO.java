package com.example.recipe_jpa.model.dto.view;

import com.example.recipe_jpa.model.entities.Ingredient;
import com.example.recipe_jpa.model.entities.Measurement;

public class RecipeIngredientDTO {

    private String id;
    private double amount;
    private Measurement measurement;
    private Ingredient ingredient;

    public RecipeIngredientDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
