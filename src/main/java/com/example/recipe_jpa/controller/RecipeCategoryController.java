package com.example.recipe_jpa.controller;

import com.example.recipe_jpa.model.dto.facade.RecipeCategoryDto;
import com.example.recipe_jpa.model.dto.form.RecipeCategoryFrom;
import com.example.recipe_jpa.services.entities.RecipeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeCategoryController {


    private final RecipeCategoryService recipeCategoryService;

    @Autowired
    public RecipeCategoryController(RecipeCategoryService recipeCategoryService) {
        this.recipeCategoryService = recipeCategoryService;
    }

    @GetMapping("/api/v1/recipe_categories/{id}")
    public ResponseEntity<RecipeCategoryDto> getById(@PathVariable("id") int id){
        RecipeCategoryDto recipeCategoryDto = recipeCategoryService.findById(id);
        return ResponseEntity.ok().body(recipeCategoryDto);
    }

    @GetMapping("/api/v1/recipe_categories")
    public ResponseEntity<List<RecipeCategoryDto>> getAll(){
        List<RecipeCategoryDto> recipeCategories = recipeCategoryService.findAll();
        return ResponseEntity.ok().body(recipeCategories);
    }

    @PostMapping("/api/v1/recipe_categories")
    public ResponseEntity<RecipeCategoryDto> create(@RequestBody RecipeCategoryFrom recipeCategoryFrom){
        RecipeCategoryDto recipeCategoryDto = recipeCategoryService.create(recipeCategoryFrom);
        return ResponseEntity.status(201).body(recipeCategoryDto);
    }


    @PutMapping("/api/v1/recipe_categories/{id}")
    public ResponseEntity<RecipeCategoryDto> update(@PathVariable("id") int id,@RequestBody RecipeCategoryFrom recipeCategoryFrom){
        RecipeCategoryDto recipeCategoryDto = recipeCategoryService.update(id,recipeCategoryFrom);
        return ResponseEntity.ok().body(recipeCategoryDto);
    }

    @DeleteMapping("/api/v1/recipe_categories/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        boolean result = recipeCategoryService.delete(id);

        if (result) return ResponseEntity.ok().body("Deleting has succeed");

        return ResponseEntity.badRequest().body("Field to delete");

    }
}
