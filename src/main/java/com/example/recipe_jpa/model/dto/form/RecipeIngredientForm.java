package com.example.recipe_jpa.model.dto.form;

import com.example.recipe_jpa.model.entities.Ingredient;
import com.example.recipe_jpa.model.entities.Measurement;
import com.example.recipe_jpa.validation.OnPost;
import com.example.recipe_jpa.validation.OnPut;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
public class RecipeIngredientForm implements Serializable {

    @NotBlank(message = "Id must not be null or empty",groups = OnPost.class)
    private String id;

    @NotBlank(message = "amout is required",groups = {OnPost.class, OnPut.class})
    private double amount;


    private Measurement measurement;



    public RecipeIngredientForm() {
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


}
