package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.entities.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCategoryDao extends JpaRepository<RecipeCategory,Integer> {
}
