package com.example.recipe_jpa.controller;

import com.example.recipe_jpa.model.dto.view.RecipeDTO;
import com.example.recipe_jpa.model.dto.form.RecipeForm;
import com.example.recipe_jpa.services.entities.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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


    @GetMapping("/api/v1/recipes/category")
    public ResponseEntity<List<RecipeDTO>> findByCategory(@RequestParam("category") String category){
        List<RecipeDTO> recipes = recipeService.findByCategory(category);
        return ResponseEntity.ok(recipes);
    }


    @GetMapping("/api/v1/recipes/categories")
    public ResponseEntity<Set<RecipeDTO>> findByAnyCategories(@RequestBody String... categories){
        Set<RecipeDTO> recipes = recipeService.findByCategories(categories);
        return ResponseEntity.ok(recipes);
    }


    @GetMapping("/api/v1/recipes/ingredient_name")
    public ResponseEntity<List<RecipeDTO>> findByIngredientName(@RequestParam("ingredientName") String ingredientName){
        List<RecipeDTO> recipes = recipeService.findByIngredientName(ingredientName);
        return ResponseEntity.ok(recipes);
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
