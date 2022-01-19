package com.example.recipe_jpa.controller;

import com.example.recipe_jpa.model.dto.facade.IngredientDto;
import com.example.recipe_jpa.model.dto.form.IngredientForm;
import com.example.recipe_jpa.services.entities.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping("/api/v1/ingredients/{id}")
    public ResponseEntity<IngredientDto> getById(@PathVariable("id") int id){
        IngredientDto ingredient = ingredientService.findById(id);
        return ResponseEntity.ok().body(ingredient);
    }

    @GetMapping("/api/v1/ingredients")
    public ResponseEntity<List<IngredientDto>> getAll(){
        List<IngredientDto> ingredients = ingredientService.findAll();
        return ResponseEntity.ok().body(ingredients);
    }

    @PostMapping("api/v1/ingredients")
    public ResponseEntity<IngredientDto> create(@RequestBody IngredientForm ingredientForm){
        IngredientDto ingredient = ingredientService.create(ingredientForm);
        return ResponseEntity.status(201).body(ingredient);
    }

    @PutMapping("/api/v1/ingredients/{id}")
    public ResponseEntity<IngredientDto> update(@PathVariable("id") int id,@RequestBody IngredientForm ingredientForm){
        IngredientDto ingredient = ingredientService.update(id,ingredientForm);
        return ResponseEntity.ok().body(ingredient);
    }


    @DeleteMapping("/api/v1/ingredients/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        boolean result = ingredientService.delete(id);

        if (result) return ResponseEntity.ok().body("deleting has succeed");

        return ResponseEntity.badRequest().body("filed to delete");
    }

}
