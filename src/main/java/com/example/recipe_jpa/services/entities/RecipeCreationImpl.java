package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeCategoryDao;
import com.example.recipe_jpa.data.DAO.RecipeDao;
import com.example.recipe_jpa.data.DAO.RecipeIngredientDao;
import com.example.recipe_jpa.model.dto.form.RecipeForm;
import com.example.recipe_jpa.model.entities.Recipe;
import com.example.recipe_jpa.model.entities.RecipeCategory;
import com.example.recipe_jpa.model.entities.RecipeIngredient;
import com.example.recipe_jpa.model.entities.RecipeInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecipeCreationImpl implements RecipeCreation{


    private RecipeDao recipeDao;
    private RecipeInstructionCreation recipeInstructionCreation;
    private RecipeCategoryDao recipeCategoryDao;
    private RecipeIngredientDao recipeIngredientDao;


    @Autowired
    public RecipeCreationImpl(RecipeDao recipeDao, RecipeInstructionCreation recipeInstructionCreation, RecipeCategoryDao recipeCategoryDao, RecipeIngredientDao recipeIngredientDao) {
        this.recipeDao = recipeDao;
        this.recipeInstructionCreation = recipeInstructionCreation;
        this.recipeCategoryDao = recipeCategoryDao;
        this.recipeIngredientDao = recipeIngredientDao;
    }

    @Override
    public Recipe save(RecipeForm recipeForm) {
        Recipe recipe = new Recipe();
        RecipeInstruction recipeInstruction = recipeInstructionCreation.save(recipeForm.getRecipeInstruction());
        List<RecipeCategory> recipeCategoryList = recipeForm.getRecipeCategoryId().stream()
                        .map(id -> recipeCategoryDao.findById(id).get())
                        .collect(Collectors.toList());
        List<RecipeIngredient> recipeIngredientList = recipeForm.getRecipeIngredientId().stream()
                        .map(id -> recipeIngredientDao.findById(id).get())
                        .collect(Collectors.toList());


        recipe.setId(recipeForm.getId());
        recipe.setRecipeName(recipeForm.getRecipeName());
        recipe.setRecipeInstruction(recipeInstruction);
        recipe.setCategories(recipeCategoryList);
        recipe.setRecipeIngredients(recipeIngredientList);

        return recipeDao.save(recipe);

    }

    @Override
    public Recipe findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("id is not correct to assign!!");

        return recipeDao.findById(id).orElseThrow(() -> new RuntimeException("Recipe is not found!!"));
    }

    @Override
    public List<Recipe> findAll() {
        return recipeDao.findAll();
    }

    @Override
    public void delete(int id) {
        if (id <= 0) throw new IllegalArgumentException("id is not correct to assign!!");

        recipeDao.deleteById(id);
    }
}
