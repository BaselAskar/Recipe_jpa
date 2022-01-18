package com.example.recipe_jpa.controller;


import com.example.recipe_jpa.model.dto.facade.RecipeDTO;
import com.example.recipe_jpa.model.dto.form.RecipeForm;
import com.example.recipe_jpa.model.entities.Recipe;
import com.example.recipe_jpa.services.entities.RecipeCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {

    private final RecipeCreation recipeCreation;

    @Autowired
    public RecipeController(RecipeCreation recipeCreation) {
        this.recipeCreation = recipeCreation;
    }


    @PostMapping("/api/create-recipe")
    public ResponseEntity<RecipeDTO> create( @RequestBody RecipeForm recipeForm){
        RecipeDTO recipeDTO = new RecipeDTO();

        Recipe recipe = recipeCreation.save(recipeForm);

        List<>

        recipeDTO.setId(recipe.getId());
        recipeDTO.setRecipeName(recipe.getRecipeName());

        return ResponseEntity.ok().body(recipeDTO);
    }
}
