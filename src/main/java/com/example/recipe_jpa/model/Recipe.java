package com.example.recipe_jpa.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String recipeName;

    @OneToMany(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "recipe"
    )
    private List<RecipeIngredient> recipeIngredients;

    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "recipes"
    )
    private List<RecipeCategory> categories;


    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "fk_recipe_instruction_id")
    private RecipeInstruction recipeInstruction;

    public Recipe() {
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

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public List<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RecipeCategory> categories) {
        this.categories = categories;
    }

    public RecipeInstruction getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(RecipeInstruction recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }
}
