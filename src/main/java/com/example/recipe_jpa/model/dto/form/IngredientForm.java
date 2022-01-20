package com.example.recipe_jpa.model.dto.form;

import com.example.recipe_jpa.validation.OnPost;
import com.example.recipe_jpa.validation.OnPut;
import com.example.recipe_jpa.validation.UniqueIngredientName;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class IngredientForm implements Serializable {

    @NotBlank(message = "id must not be null or empty!",groups = OnPut.class)
    private int id;

    @NotBlank(message = "ingredient name has to be existed!!",groups = {OnPost.class})
    @UniqueIngredientName(message = "This ingredient is already existed!!",groups = {OnPost.class,OnPut.class})
    private String ingredientName;

    public IngredientForm() {
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
