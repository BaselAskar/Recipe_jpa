package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface RecipeDao extends JpaRepository<Recipe,Integer> {
    //First function
    List<Recipe> findAllByRecipeNameContainingIgnoringCase(String name);

    @Query("SELECT r FROM Recipe r WHERE UPPER(r.recipeName) LIKE UPPER(CONCAT('%',:name,'%') ) ")
    List<Recipe> searchByRecipeName(@Param("name") String name);

    //Second function
    List<Recipe> findAllByRecipeIngredientsIngredientIngredientNameIgnoringCase(String name);

    @Query("SELECT ri.recipe FROM RecipeIngredient ri WHERE ri.ingredient.ingredientName = :name ")
    List<Recipe> searchByIngredientName(@Param("name") String name);

    //Third function
    List<Recipe> findAllByCategoriesCategory(String category);

    @Query("SELECT rc.recipes FROM RecipeCategory rc WHERE rc.category = :category ")
    List<Recipe> searchByCategory(@Param("category") String category);

    //Forth function

    @Query("SELECT rc.recipes FROM RecipeCategory rc WHERE rc.category IN :categories")
    Set<Recipe> searchByAnyCategories(@Param("categories") String... categories);
}
