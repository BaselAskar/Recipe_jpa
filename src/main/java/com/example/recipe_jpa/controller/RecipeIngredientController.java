package com.example.recipe_jpa.controller;

import com.example.recipe_jpa.model.dto.view.RecipeIngredientDTO;
import com.example.recipe_jpa.model.dto.form.RecipeIngredientForm;
import com.example.recipe_jpa.services.entities.RecipeIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeIngredientController {


    private final RecipeIngredientService recipeIngredientService;

    @Autowired
    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @GetMapping("/api/v1/recipe_ingredients/{id}")
    public ResponseEntity<RecipeIngredientDTO> getById(@PathVariable("id") String id){
        RecipeIngredientDTO recipeIngredientDTO = recipeIngredientService.findById(id);
        return ResponseEntity.ok().body(recipeIngredientDTO);
    }

    @GetMapping("/api/v1/recipe_ingredients")
    public ResponseEntity<List<RecipeIngredientDTO>> getAll(){
        List<RecipeIngredientDTO> recipeIngredients = recipeIngredientService.findAll();
        return ResponseEntity.ok().body(recipeIngredients);
    }

    @PostMapping("/api/v1/recipe_ingredients")
    public ResponseEntity<RecipeIngredientDTO> create(@RequestBody RecipeIngredientForm recipeIngredientForm){
        RecipeIngredientDTO recipeIngredientDTO = recipeIngredientService.create(recipeIngredientForm);
        return ResponseEntity.status(201).body(recipeIngredientDTO);
    }

    @PutMapping("/api/v1/recipe_ingredients/{id}")
    public ResponseEntity<RecipeIngredientDTO> update(@PathVariable("id") String id,@RequestBody RecipeIngredientForm recipeIngredientForm){
        RecipeIngredientDTO recipeIngredientDTO = recipeIngredientService.update(id,recipeIngredientForm);
        return ResponseEntity.ok().body(recipeIngredientDTO);
    }

    @DeleteMapping("/api/v1/recipe_ingredients/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        boolean result = recipeIngredientService.delete(id);

        if (result){
            return ResponseEntity.ok().body("Deleting has succeed");
        }else {
          return ResponseEntity.badRequest().body("Field to delete");
        }
    }
}
