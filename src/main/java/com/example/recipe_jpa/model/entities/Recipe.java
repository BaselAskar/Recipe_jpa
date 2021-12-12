package com.example.recipe_jpa.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
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

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, List<RecipeCategory> categories, RecipeInstruction recipeInstruction) {
        setRecipeName(recipeName);
        setRecipeIngredients(recipeIngredients);
        setCategories(categories);
        setRecipeInstruction(recipeInstruction);
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
        if (recipeIngredients == null) recipeIngredients = new ArrayList<>();
        if (recipeIngredients.isEmpty()){
            if (this.recipeIngredients != null){
                this.recipeIngredients.forEach(re -> re.setRecipe(null));
            }
        }else {
            recipeIngredients.forEach(re -> re.setRecipe(this));
        }
        this.recipeIngredients = recipeIngredients;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredient == null) throw new IllegalArgumentException("this recipe ingredient is null!!");

        if (this.recipeIngredients == null) this.recipeIngredients = new ArrayList<>();

        if (!this.recipeIngredients.contains(recipeIngredient)){
            this.recipeIngredients.add(recipeIngredient);

            recipeIngredient.setRecipe(this);
        }

    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if (recipeIngredient == null) throw new IllegalArgumentException("this recipe ingredient is null!!");

        if (this.recipeIngredients.contains(recipeIngredient)){
            this.recipeIngredients.remove(recipeIngredient);
            recipeIngredient.setRecipe(null);
        }
    }

    public List<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<RecipeCategory> categories) {
        if (categories == null) categories = new ArrayList<>();


        if (categories.isEmpty()){
            if (this.categories != null){
                this.categories.forEach(ca -> {
                    if (ca.getRecipes() != null){
                        ca.getRecipes().remove(this);
                    }
                });
            }
        }else {
            categories.forEach(ca -> {
                if (ca.getRecipes() == null) ca.setRecipes(new ArrayList<>());
                ca.getRecipes().add(this);
            });
        }

        this.categories = categories;
    }

    public void addCategory(RecipeCategory recipeCategory){
        if (recipeCategory == null) throw new IllegalArgumentException("this recipe category is null");

        if (this.categories == null) this.categories = new ArrayList<>();

        this.categories.add(recipeCategory);
        recipeCategory.getRecipes().add(this);
    }

    public void removeCategory(RecipeCategory recipeCategory){
        if (recipeCategory == null) throw new IllegalArgumentException("this category is null!!");

        if (this.categories == null) this.categories = new ArrayList<>();

        this.categories.remove(recipeCategory);
        recipeCategory.getRecipes().remove(this);
    }

    public RecipeInstruction getRecipeInstruction() {
        return recipeInstruction;
    }

    public void setRecipeInstruction(RecipeInstruction recipeInstruction) {
        this.recipeInstruction = recipeInstruction;
    }
}
