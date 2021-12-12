package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.entities.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientDao extends JpaRepository<RecipeIngredient,String> {
}
