package com.example.recipe_jpa.model.dto.form;

import com.example.recipe_jpa.validation.OnPost;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Validated
public class RecipeInstructionForm implements Serializable {

    @NotBlank(message = "Id must be existed",groups = {OnPost.class})
    private String id;

    @NotBlank
    private String instructions;

    public RecipeInstructionForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
