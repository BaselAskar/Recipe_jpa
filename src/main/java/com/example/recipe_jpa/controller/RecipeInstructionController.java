package com.example.recipe_jpa.controller;

import com.example.recipe_jpa.model.dto.facade.RecipeInstructionDto;
import com.example.recipe_jpa.model.dto.form.RecipeInstructionForm;
import com.example.recipe_jpa.services.entities.RecipeInstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeInstructionController {


    private final RecipeInstructionService recipeInstructionService;

    @Autowired
    public RecipeInstructionController(RecipeInstructionService recipeInstructionService) {
        this.recipeInstructionService = recipeInstructionService;
    }

    @GetMapping("/api/v1/recipe_instructions/{id}")
    public ResponseEntity<RecipeInstructionDto> getById(@PathVariable("id") String id){
        RecipeInstructionDto recipeInstructionDto = recipeInstructionService.findById(id);
        return ResponseEntity.ok().body(recipeInstructionDto);
    }


    @GetMapping("/api/v1/recipe_instructions")
    public ResponseEntity<List<RecipeInstructionDto>> getAll(){
        List<RecipeInstructionDto> recipeInstructions = recipeInstructionService.findAll();
        return ResponseEntity.ok().body(recipeInstructions);
    }


    @PostMapping("/api/v1/recipe_instructions")
    public ResponseEntity<RecipeInstructionDto> create(@RequestBody RecipeInstructionForm recipeInstructionForm){
        RecipeInstructionDto recipeInstructionDto = recipeInstructionService.create(recipeInstructionForm);
        return ResponseEntity.status(201).body(recipeInstructionDto);
    }


    @PutMapping("/api/v1/recipe_instructions/{id}")
    public ResponseEntity<RecipeInstructionDto> update(@PathVariable("id") String id,@RequestBody RecipeInstructionForm recipeInstructionForm){
        RecipeInstructionDto recipeInstructionDto = recipeInstructionService.update(id,recipeInstructionForm);
        return ResponseEntity.ok().body(recipeInstructionDto);
    }


    @DeleteMapping("/api/v1/recipe_instructions/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id){
        boolean result = recipeInstructionService.delete(id);

        if (result){
            return ResponseEntity.ok().body("Deleting has succeed");
        }else {
            return ResponseEntity.badRequest().body("Field to delete");
        }
    }
}
