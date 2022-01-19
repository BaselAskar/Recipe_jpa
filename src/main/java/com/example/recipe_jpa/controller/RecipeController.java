package com.example.recipe_jpa.controller;

import com.example.recipe_jpa.model.dto.facade.RecipeDTO;
import com.example.recipe_jpa.model.dto.form.RecipeForm;
import com.example.recipe_jpa.services.entities.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/v1/recipes/{id}")
    public ResponseEntity<RecipeDTO> getById(@PathVariable("id") int id){
        RecipeDTO recipeDTO = recipeService.findById(id);
        return ResponseEntity.ok().body(recipeDTO);
    }


    @GetMapping("/api/v1/recipes")
    public ResponseEntity<List<RecipeDTO>> getAll(){
        List<RecipeDTO> recipes = recipeService.findAll();
        return ResponseEntity.ok().body(recipes);
    }


    @PostMapping("/api/v1/recipes")
    public ResponseEntity<RecipeDTO> create(@RequestBody RecipeForm recipeForm){
        RecipeDTO recipeDTO = recipeService.create(recipeForm);
        return ResponseEntity.status(201).body(recipeDTO);
    }


    @PutMapping("/api/v1/recipes/{id}")
    public ResponseEntity<RecipeDTO> update(@PathVariable("id") int id,@RequestBody RecipeForm recipeForm){
        RecipeDTO recipeDTO = recipeService.update(id,recipeForm);
        return ResponseEntity.ok().body(recipeDTO);
    }


    @DeleteMapping("/api/v1/recipes/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        boolean result = recipeService.delete(id);

        if (result){
            return ResponseEntity.ok().body("Deleting has succeed");
        }else {
            return ResponseEntity.badRequest().body("Filed to delete");
        }
    }
}
