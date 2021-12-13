package com.example.recipe_jpa.services.entities;

import com.example.recipe_jpa.data.DAO.RecipeInstructionDao;
import com.example.recipe_jpa.model.dto.form.RecipeInstructionForm;
import com.example.recipe_jpa.model.entities.RecipeInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeInstructionCreationImpl implements RecipeInstructionCreation{

    private RecipeInstructionDao recipeInstructionDao;

    @Autowired
    public RecipeInstructionCreationImpl(RecipeInstructionDao recipeInstructionDao) {
        this.recipeInstructionDao = recipeInstructionDao;
    }

    @Override
    public RecipeInstruction save(RecipeInstructionForm recipeInstructionForm) {
        RecipeInstruction recipeInstruction = new RecipeInstruction();

        recipeInstruction.setId(recipeInstructionForm.getId());
        recipeInstruction.setInstructions(recipeInstructionForm.getInstructions());

        return recipeInstructionDao.save(recipeInstruction);
    }

    @Override
    public RecipeInstruction findById(String id) {
        Optional<RecipeInstruction> recipeInstruction = recipeInstructionDao.findById(id);
        return recipeInstruction.orElseThrow(() -> new RuntimeException("recipe instruction not found!!"));
    }

    @Override
    public List<RecipeInstruction> findAll() {
        return recipeInstructionDao.findAll();
    }

    @Override
    public void delete(String id) {
        recipeInstructionDao.deleteById(id);
    }
}
