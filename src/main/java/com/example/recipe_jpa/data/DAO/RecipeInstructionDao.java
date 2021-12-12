package com.example.recipe_jpa.data.DAO;

import com.example.recipe_jpa.model.RecipeInstruction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeInstructionDao extends JpaRepository<RecipeInstruction,String> {
}
