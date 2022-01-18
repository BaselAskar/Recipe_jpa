package com.example.recipe_jpa.model.dto.facade;

public class RecipeInstructionDto {

    private String id;
    private String instructions;

    public RecipeInstructionDto() {
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
