package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientDao extends JpaRepository<Ingredient,Integer> {

    Ingredient findByIngredientNameIgnoringCase(String name);

    List<Ingredient> findAllByIngredientNameContainingIgnoringCase(String name);

    Ingredient findByIngredientName(String name);
}
