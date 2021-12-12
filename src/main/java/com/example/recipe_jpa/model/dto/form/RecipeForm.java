package com.example.recipe_jpa.model.dto.form;

import com.example.recipe_jpa.validation.OnPost;
import com.example.recipe_jpa.validation.OnPut;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
public class RecipeForm implements Serializable {

    @NotBlank(message = "Id must not be null or empty",groups = OnPost.class)
    private int id;

    @NotBlank(message = "You have to insert recipe name!",groups = {OnPost.class, OnPut.class})
    private String recipeName;

    @NotNull(message = "Recipe instructions must not be null!",groups = {OnPost.class,OnPut.class})
    @Valid
    private RecipeInstructionForm recipeInstruction;

    public RecipeForm() {
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

    public RecipeInstructionForm getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(RecipeInstructionForm recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }
}
