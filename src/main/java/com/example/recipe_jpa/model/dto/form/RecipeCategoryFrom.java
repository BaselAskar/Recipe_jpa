package com.example.recipe_jpa.model.dto.form;


import com.example.recipe_jpa.validation.OnPost;
import com.example.recipe_jpa.validation.OnPut;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Validated
public class RecipeCategoryFrom implements Serializable {

    @NotBlank(message = "Id must not be null or empty",groups = OnPost.class)
    private int id;

    @NotBlank(message = "You have to insert category",groups = {OnPost.class, OnPut.class})
    private String category;

    public RecipeCategoryFrom() {
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
}
